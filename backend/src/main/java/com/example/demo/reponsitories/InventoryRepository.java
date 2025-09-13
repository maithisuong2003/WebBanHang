package com.example.demo.reponsitories;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.InventoryEntity;
import com.example.demo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity,Integer> {
    Optional<InventoryEntity> findByProductEntity(ProductEntity productEntity);
}
