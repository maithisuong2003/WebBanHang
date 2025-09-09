package com.example.demo.dto.request;

import lombok.Data;

@Data
public class ProductRequest {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private Integer categoryId;
    private String categoryName;
}