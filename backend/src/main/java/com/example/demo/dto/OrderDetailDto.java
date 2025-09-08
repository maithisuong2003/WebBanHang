package com.example.demo.dto;

import lombok.Data;

@Data
public class OrderDetailDto {
    private Integer id;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Integer quantity;
    private Double price;
}