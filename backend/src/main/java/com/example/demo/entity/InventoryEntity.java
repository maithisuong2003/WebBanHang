package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventorys")
@Data
@ToString
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_product", nullable = false)
    @JsonBackReference
    private ProductEntity productEntity;

    private Long quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdd;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;

    public void setDateAdd() {
        this.dateAdd = new Date();
    }

    public void setLastUpdatedDate() {
        this.lastUpdatedDate = new Date();
    }
}