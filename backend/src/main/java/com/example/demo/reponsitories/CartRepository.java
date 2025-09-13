package com.example.demo.reponsitories;

import com.example.demo.entity.CartEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    Optional<CartEntity> findByUserEntity(UserEntity userEntity);

}