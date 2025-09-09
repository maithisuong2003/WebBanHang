package com.example.demo.dto.response;

public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private Boolean isActive;
    private String categoryName; // Thay vì trả cả categoryEntity
    private String status;
}
