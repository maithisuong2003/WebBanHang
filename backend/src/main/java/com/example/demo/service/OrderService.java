package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.request.OrderRequest;
import com.example.demo.dto.response.OrderResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.entity.*;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.reponsitories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    @Transactional
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        // Lấy giỏ hàng hiện tại của user (bạn đã có service này)
        CartEntity cart = cartService.getMyCart();
        List<CartItemEntity> cartItems = cart.getCartItems();

        // Tạo mới OrderEntity từ thông tin orderRequest và cart
        OrderEntity order = getOrderEntity(orderRequest, cart);

        // Xử lý trạng thái thanh toán
        if ("Đã thanh toán".equals(orderRequest.getStatusPay())) {
            order.setStatusPay("Đã thanh toán");
        } else {
            order.setStatusPay("Chưa thanh toán");
        }

        // Lưu order trước để có id cho order detail
        orderRepository.save(order);

        // Duyệt từng CartItem để tạo OrderDetail và trừ kho
        for (CartItemEntity cartItem : cartItems) {
            OrderDetailEntity orderDetail = new OrderDetailEntity();
            orderDetail.setOrderEntity(order);
            orderDetail.setProductEntity(cartItem.getProductEntity());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getPrice());

            // Kiểm tra và trừ kho
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

        // Cập nhật lại giỏ hàng
        cart.getCartItems().clear();
        cart.setUpdatedAt(new Date());
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);

        return orderMapper.toOrderResponse(order);
    }

    // Hàm tạo OrderEntity từ OrderRequest và CartEntity
    private OrderEntity getOrderEntity(OrderRequest orderRequest, CartEntity cart) {
        OrderEntity order = new OrderEntity();
        order.setAddress(orderRequest.getAddress());
        order.setDeliveryAt(orderRequest.getDeliveryAt());
        order.setNote(orderRequest.getNote());
        order.setSale(orderRequest.getSale());
        order.setCreateAt(new Date());
        order.setUpdateAt(new Date());
        order.setFree_ship(40000.0); // hoặc tự tính nếu cần
        order.setUserEntity(cart.getUserEntity()); // đổi thành getUserEntity nếu đúng entity bạn dùng
        order.setStatus("Chờ xác nhận");
        order.setTotalPrice(cart.getTotalPrice());
        return order;
    }
@Override
    public List<OrderResponse> getOrders() {
        return null;
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
