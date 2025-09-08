package com.example.demo.entity;

import com.example.demo.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private double price;
    private String imageUrl;

    private Boolean isActive;
    private Boolean isDelete;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity categoryEntity;
}