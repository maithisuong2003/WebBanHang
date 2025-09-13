package com.example.demo.reponsitories;

import com.example.demo.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {
    List<CartItemEntity> findByCartEntityId(Integer cartId);
}