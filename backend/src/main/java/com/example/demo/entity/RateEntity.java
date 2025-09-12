package com.example.demo.entity;
import lombok.Data;

import jakarta.persistence.*;


@Entity
@Table(name = "rates")
@Data
public class RateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_account")
    private UserEntity accountEntity;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private ProductEntity productEntity;

    private Long numberStar;

    private int numberEdit;

    private String status;
}