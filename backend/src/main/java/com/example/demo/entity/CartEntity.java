package com.example.demo.entity;

import lombok.Data;
import jakarta.persistence.*;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "carts")
@Data
@ToString
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "datetime default current_timestamp")
    private Date createdAt;

    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity userEntity;

    @OneToMany(mappedBy = "cartEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItems  = new ArrayList<>();

    private Double totalPrice;

    private String status;
}