package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.request.OrderRequest;
import com.example.demo.dto.response.OrderResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.entity.*;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.reponsitories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final InventoryRepository inventoryRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailRepository orderDetailRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        CartEntity cart = cartService.getMyCart();
        List<CartItemEntity> cartItems = cart.getCartItems();

        OrderEntity order = getOrderEntity(orderRequest, cart);

        if ("Đã thanh toán".equals(orderRequest.getStatusPay())) {
            order.setStatusPay("Đã thanh toán");
        } else {
            order.setStatusPay("Chưa thanh toán");
        }

        orderRepository.save(order);

        for (CartItemEntity cartItem : cartItems) {
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setOrderEntity(order);
            orderDetail.setProductEntity(cartItem.getProductEntity());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getPrice());

            InventoryEntity inventory = inventoryRepository.findByProductEntity(cartItem.getProductEntity())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy tồn kho cho sản phẩm: " + cartItem.getProductEntity().getId()));
            if (inventory.getQuantity() < cartItem.getQuantity()) {
                throw new RuntimeException("Sản phẩm " + cartItem.getProductEntity().getId() + " không đủ hàng trong kho");
            }
            inventory.setQuantity(inventory.getQuantity() - cartItem.getQuantity());
            inventory.setLastUpdatedDate();
            inventoryRepository.save(inventory);

            order.getOrderItems().add(orderDetail);
            orderDetailRepository.save(orderDetail);

            cartItemRepository.delete(cartItem); // Xóa item khỏi giỏ khi đã đặt hàng
        }

        cart.getCartItems().clear();
        cart.setUpdatedAt(new Date());
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);

        return orderMapper.toOrderResponse(order);
    }

    private OrderEntity getOrderEntity(OrderRequest orderRequest, CartEntity cart) {
        OrderEntity order = new OrderEntity();
        order.setAddress(orderRequest.getAddress());
        order.setDeliveryAt(orderRequest.getDeliveryAt());
        order.setNote(orderRequest.getNote());
        order.setSale(orderRequest.getSale());
        order.setCreateAt(new Date());
        order.setUpdateAt(new Date());
        order.setFree_ship(40000.0);
        order.setUserEntity(cart.getUserEntity());
        order.setStatus("Chờ xác nhận");
        order.setTotalPrice(cart.getTotalPrice());
        return order;
    }
@Override
    public List<OrderResponse> getOrders() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUserName(name)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_FOUND));
        List<OrderEntity> orderEntityList = orderRepository.findByUserEntity(userEntity);
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for(OrderEntity order : orderEntityList){
            OrderResponse orderResponse = orderMapper.toOrderResponse(order);
            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }

    @Override
    public List<OrderResponse> getOrdersByAdmin() {
        return null;
    }

    @Override
    public OrderResponse getOrderById(Integer orderId) {
        return null;
    }

    @Override
    public void updateOrderStatus(Integer orderId, String status) {

    }

    @Override
    public void cancelOrder(Integer orderId) {

    }

    @Override
    public Long getTotalOrders() {
        return null;
    }

    @Override
    public Double getTotalAmount() {
        return null;
    }
}
