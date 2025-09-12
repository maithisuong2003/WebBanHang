package com.example.demo.entity;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "suppliers")
@Data
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nameSupplier;

    private String isActive;
}