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

    private String nameProduct;
    private String description;
    private double price;
    private String imageUrl;
    private Boolean isActive;
    private Boolean isDelete;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @ManyToOne
    @JoinColumn(name="supplier_id")
    private SupplierEntity supplierEntity;
    @ManyToOne
    @JoinColumn(name="producer_id")
    private ProducerEntity producerEntity;
    @ManyToOne
    @JoinColumn(name="category_id")
    private CategoryEntity categoryEntity;
    @OneToOne(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private InventoryEntity inventoryEntity;
    @Column(nullable = false)
    private Double discount = 0.0; // Thêm trường này

    // Hàm tính giá sau giảm
    public double getDiscountPrice() {
        return price - (price * discount / 100);
    }
}