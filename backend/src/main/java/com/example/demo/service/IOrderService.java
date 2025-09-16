package com.example.demo.service;

import com.example.demo.dto.request.OrderRequest;
import com.example.demo.dto.response.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse saveOrder(OrderRequest orderRequest);
    List<OrderResponse> getOrders();
    List<OrderResponse> getOrdersByAdmin();
    OrderResponse getOrderById(Integer orderId);
    void updateOrderStatus(Integer orderId, String status);
    void cancelOrder(Integer orderId);
    Long getTotalOrders();
    Double getTotalAmount();
}
