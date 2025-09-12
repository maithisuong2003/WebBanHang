package com.example.demo.util;

import com.example.demo.dto.ProductFilterDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProducerEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.SupplierEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<ProductEntity> getSpecifications(ProductFilterDto filter, Pageable pageable) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getCategories() != null && !filter.getCategories().isEmpty()) {
                Join<ProductEntity, CategoryEntity> categoryJoin = root.join("categoryEntity");
                predicates.add(categoryJoin.get("nameCategory").in(filter.getCategories()));
            }

            if (filter.getMinPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMinPrice()));
            }
            if (filter.getMaxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMaxPrice()));
            }

            if (filter.getSuppliers() != null && !filter.getSuppliers().isEmpty()) {
                Join<ProductEntity, SupplierEntity> supplierJoin = root.join("supplierEntity");
                predicates.add(supplierJoin.get("nameSupplier").in(filter.getSuppliers()));
            }

            if (filter.getProducers() != null && !filter.getProducers().isEmpty()) {
                Join<ProductEntity, ProducerEntity> producerJoin = root.join("producerEntity");
                predicates.add(producerJoin.get("nameProducer").in(filter.getProducers()));
            }

            if (filter.getSortBy() != null && !filter.getSortBy().isEmpty()) {
                if (filter.getSortDirection() != null && filter.getSortDirection().equalsIgnoreCase("ASC")) {
                    query.orderBy(cb.asc(root.get(filter.getSortBy())));
                } else {
                    query.orderBy(cb.desc(root.get(filter.getSortBy())));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}