package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Integer id;
    private Integer userId;
    private String userName;
    private LocalDateTime orderDate;
    private Double total;
    private String status;
}