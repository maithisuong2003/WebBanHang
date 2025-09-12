package com.example.demo.reponsitories;

import com.example.demo.entity.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity,Integer> {
    public boolean existsBynameSupplier(String name);
}
