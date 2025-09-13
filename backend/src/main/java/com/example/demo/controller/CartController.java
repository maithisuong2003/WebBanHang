package com.example.demo.controller;

import com.example.demo.dto.request.CartItemRequest;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.dto.response.CartItemResponse;
import com.example.demo.entity.CartEntity;
import com.example.demo.entity.CartItemEntity;
import com.example.demo.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/carts")
@AllArgsConstructor
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping({"/", "/all", ""})
    @PreAuthorize("hasAuthority('CART_GET')")
    public ApiResponse<List<CartEntity>> getAllCarts() {
        return ApiResponse.<List<CartEntity>>builder()
                .code(200)
                .result(cartService.getAllCarts())
                .build();
    }
    @PostMapping("/add-item")
    @PreAuthorize("hasAuthority('CART_POST')")
    public ApiResponse<CartItemResponse> addItemToCart(@RequestBody CartItemRequest cartItemDto) {
        return ApiResponse.<CartItemResponse>builder()
                .code(200)
                .result(cartService.addItemToCart(cartItemDto))
                .build();
    }
    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('CART_GET')")
    public ApiResponse<CartEntity> getCart(@PathVariable Integer userId) {
        return ApiResponse.<CartEntity>builder()
                .code(200)
                .result(cartService.getCartByUserId(userId))
                .build();
    }
    @GetMapping("/cart-items/{cartId}")
    @PreAuthorize("hasAuthority('CART_GET')")
    public ApiResponse<List<CartItemEntity>> getCartItemsByCartId(@PathVariable Integer cartId) {
        return ApiResponse.<List<CartItemEntity>>builder()
                .code(200)
                .result(cartService.getCartItemByCartId(cartId))
                .build();
    }
    @GetMapping("/my-cart")
    @PreAuthorize("hasAuthority('CART_GET')")
    public ApiResponse<CartEntity> getMyCart() {
        return ApiResponse.<CartEntity>builder()
                .code(200)
                .result(cartService.getMyCart())
                .build();
    }
    @GetMapping("/total-items/{userId}")
    @PreAuthorize("hasAuthority('CART_GET')")
    public Integer getTotalItemsInCart(@PathVariable Integer userId) {
        return ApiResponse.<Integer>builder()
                .code(200)
                .result(cartService.getTotalItemsInCart(userId))
                .build().getResult();
    }
    @PutMapping("/increase-quantity/{cartItemId}")
    public ApiResponse<Void> increaseQuantity(@PathVariable("cartItemId") Integer cartItemId) {
        cartService.increaseQuantity(cartItemId);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Quantity increased!")
                .build();
    }
    @PutMapping("/decrease-quantity/{cartItemId}")
    public ApiResponse<Void> decreaseQuantity(@PathVariable("cartItemId") Integer cartItemId) {
        cartService.decreaseQuantity(cartItemId);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Quantity decreased!")
                .build();
    }
    @DeleteMapping("/remove-item/{cartItemId}")
    @PreAuthorize("hasAuthority('CART_DELETE')")
    public ApiResponse<Void> removeItemFromCart(@PathVariable("cartItemId") Integer cartItemId) {
        cartService.removeItemFromCart(cartItemId);
        return ApiResponse.<Void>builder()
                .code(200)
                .message("Item removed from cart!")
                .build();
    }
}
