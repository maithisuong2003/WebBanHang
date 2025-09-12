package com.example.demo.dto;

import com.example.demo.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Integer id;
    private String nameProduct;
    private String description;
    private double price;
    private SupplierEntity supplierEntity;
    private ProducerEntity producerEntity;
    private CategoryEntity categoryEntity;
    private String imageProductEntity;
    private List<RateEntity> ratesEntity;
    private List<ImportCouponDetailEntity> importCouponDetailsEntity;
    private List<OrderDetailEntity> orderDetailsEntity;
}

