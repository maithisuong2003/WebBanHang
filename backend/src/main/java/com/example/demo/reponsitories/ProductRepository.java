package com.example.demo.reponsitories;

import com.example.demo.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>, JpaSpecificationExecutor<ProductEntity> {
    Page<ProductEntity> findAll(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.categoryEntity.id = ?1")
    List<ProductEntity> findProductByCategoryId(Long categoryId, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.isDelete = :isDelete")
    List<ProductEntity> getProductByAdmin(@Param("isDelete") boolean isDelete);
    @Query("SELECT p FROM ProductEntity p ORDER BY p.id DESC")
    List<ProductEntity> findLatestProducts(Pageable pageable);
   Page<ProductEntity> findByNameProductContainingIgnoreCase(String nameProduct, Pageable pageable);
}
