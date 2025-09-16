package com.example.demo.reponsitories;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {
    List<OrderEntity> findByUserEntity(UserEntity userEntity);
}
