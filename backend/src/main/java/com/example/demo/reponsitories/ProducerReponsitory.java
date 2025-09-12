package com.example.demo.reponsitories;

import com.example.demo.entity.ProducerEntity;
import com.example.demo.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerReponsitory extends JpaRepository<ProducerEntity,Integer> {
    public boolean existsByNameProducer(String name);
}
