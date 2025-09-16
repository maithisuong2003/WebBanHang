package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "order_details")
@Data
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_order", nullable = false)
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private ProductEntity productEntity;

    private Integer quantity;

    private Double price;

}