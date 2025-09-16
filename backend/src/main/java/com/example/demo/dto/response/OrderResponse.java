package com.example.demo.dto.response;

import com.example.demo.entity.OrderDetailEntity;
import com.example.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {
    private Integer id;

    private Date createAt;

    private Date deliveryAt;

    private String statusPay;

    private String sale;

    private Double free_ship;

    private Double totalPrice;

    private String status;

    private String address;

    private List<OrderDetailEntity> orderItems  = new ArrayList<>();

    private UserEntity userEntity;

    private String note;

    private Date updateAt;
}