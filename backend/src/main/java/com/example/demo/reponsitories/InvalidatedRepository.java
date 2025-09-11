package com.example.demo.reponsitories;

import com.example.demo.entity.InvalidatedTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedRepository extends JpaRepository<InvalidatedTokenEntity, String> {
}
