package com.example.demo.service;

import com.example.demo.dto.request.CartItemRequest;
import com.example.demo.dto.response.CartItemResponse;
import com.example.demo.entity.*;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.CartMapper;
import com.example.demo.reponsitories.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private InventoryRepository inventoryRepository;
    public List<CartEntity> getAllCarts() {
        return cartRepository.findAll();
    }
    @Transactional
    public CartItemResponse addItemToCart(CartItemRequest cartItemRequest) {
        UserEntity userEntity = userRepository.findById(cartItemRequest.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        CartEntity cartEntity = cartRepository.findByUserEntity(userEntity).orElse(null);
        if (cartEntity == null) {
            cartEntity = new CartEntity();
            cartEntity.setUserEntity(userEntity);
            cartEntity.setCreatedAt(new Date());
            cartEntity.setUpdatedAt(new Date());
            cartEntity.setStatus("active");
            cartEntity.setTotalPrice(0.0);
            cartEntity.setCartItems(new ArrayList<>());
            cartRepository.save(cartEntity);
        } else {
            cartEntity.setUpdatedAt(new Date());
            if (cartEntity.getCartItems() == null) {
                cartEntity.setCartItems(new ArrayList<>());
            }
        }

        ProductEntity productEntity = productRepository.findById(cartItemRequest.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        CartItemEntity existingItem = cartEntity.getCartItems()
                .stream()
                .filter(item -> item != null && item.getProductEntity() != null
                        && item.getProductEntity().getId().equals(productEntity.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + cartItemRequest.getQuantity();
            existingItem.setQuantity(newQuantity);
            existingItem.setPrice(productEntity.getPrice() * newQuantity);
        } else {
            CartItemEntity newCartItem = new CartItemEntity();
            newCartItem.setCartEntity(cartEntity);
            newCartItem.setProductEntity(productEntity);
            newCartItem.setQuantity(cartItemRequest.getQuantity());
            newCartItem.setPrice(productEntity.getPrice() * cartItemRequest.getQuantity());
            cartEntity.getCartItems().add(newCartItem);
        }

        double totalPrice = cartEntity.getCartItems()
                .stream()
                .filter(Objects::nonNull)
                .mapToDouble(item -> item.getPrice() != null ? item.getPrice() : 0.0)
                .sum();
        cartEntity.setTotalPrice(totalPrice);

        cartRepository.save(cartEntity);

        CartItemEntity resultItem = cartEntity.getCartItems()
                .stream()
                .filter(item -> item != null && item.getProductEntity() != null
                        && item.getProductEntity().getId().equals(productEntity.getId()))
                .findFirst()
                .orElse(null);

        return cartMapper.mapToCartItemDto(resultItem);
    }
    public CartEntity getCartByUserId(Integer userId) {
        UserEntity account = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return cartRepository.findByUserEntity(account).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
    }
    public List<CartItemEntity> getCartItemByCartId(Integer cartId){
        return cartItemRepository.findByCartEntityId(cartId);
    }
    public CartEntity getMyCart(){
        var context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return cartRepository.findByUserEntity(userEntity)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }
    public Integer getTotalItemsInCart(Integer userId){
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        CartEntity cart = cartRepository.findByUserEntity(userEntity).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        List<CartItemEntity> cartItemEntities = getCartItemByCartId(cart.getId());
        return cartItemEntities.stream().mapToInt(CartItemEntity :: getQuantity).sum();
    }
    @Transactional
    public void increaseQuantity(Integer cartItemId) {
        var context = SecurityContextHolder.getContext();
        String accountName = context.getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUserName(accountName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        CartEntity cart = cartRepository.findByUserEntity(userEntity)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        CartItemEntity cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));
        ProductEntity product = cartItem.getProductEntity();
        InventoryEntity inventory = inventoryRepository.findByProductEntity(product)
                .orElseThrow(() -> new AppException(ErrorCode.INVENTORY_NOT_FOUND));
        if (cartItem.getQuantity() + 1 > inventory.getQuantity()) {
            throw new AppException(ErrorCode.NOT_ENOUGH_QUANTITY);
        }

        cartItem.setQuantity(cartItem.getQuantity() + 1);
        double price = product.getDiscountPrice();
        cartItem.setPrice(price * cartItem.getQuantity());
        cartItemRepository.save(cartItem);
        cart.setUpdatedAt(new Date());
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItemEntity::getPrice)
                .sum();
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }
    @Transactional
    public void decreaseQuantity(Integer cartItemId) {
        var context = SecurityContextHolder.getContext();
        String accountName = context.getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUserName(accountName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        CartEntity cart = cartRepository.findByUserEntity(userEntity)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        CartItemEntity cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));
        ProductEntity product = cartItem.getProductEntity();
        int newQuantity = cartItem.getQuantity() - 1;
        if (newQuantity <= 0) {
            cart.getCartItems().remove(cartItem);
            cartItemRepository.delete(cartItem); 
        } else {
            cartItem.setQuantity(newQuantity);
            double price = product.getDiscountPrice();
            cartItem.setPrice(price * cartItem.getQuantity());
            cartItemRepository.save(cartItem);
        }

        cart.setUpdatedAt(new Date());
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItemEntity::getPrice)
                .sum();
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }
    @Transactional
    public void removeItemFromCart(Integer cartItemId){
        var context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        UserEntity userEntity = userRepository.findByUserName(userName)
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_FOUND));
        CartEntity cart = cartRepository.findByUserEntity(userEntity)
                .orElseThrow(()->new AppException(ErrorCode.CART_NOT_FOUND));
        CartItemEntity cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(()->new AppException(ErrorCode.CART_NOT_FOUND));
        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cart.setUpdatedAt(new Date());
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(CartItemEntity::getPrice)
                .sum();
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);
    }
}
