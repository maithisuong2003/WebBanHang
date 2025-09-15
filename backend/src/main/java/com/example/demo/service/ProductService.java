package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductFilterDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProducerEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.SupplierEntity;
import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.reponsitories.CategoryRepository;
import com.example.demo.reponsitories.ProducerReponsitory;
import com.example.demo.reponsitories.ProductRepository;
import com.example.demo.reponsitories.SupplierRepository;
import com.example.demo.util.ProductSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor

public class ProductService implements IProductService {
    private ProductRepository iProductRepository;
    private ProductMapper iProductMapper;
    private CategoryRepository categoryRepository;
    private SupplierRepository supplierRepository;
    private ProducerReponsitory producerReponsitory;
    @Override
    public Page<ProductDto> getAllProduct(Pageable pageable, ProductFilterDto filterDto) {
        Page<ProductEntity> products = iProductRepository.findAll(ProductSpecification.getSpecifications(filterDto,pageable),pageable);
        List<ProductDto> productDtos = products.stream().map(product -> iProductMapper.mapToProductDto(product))
                .collect(Collectors.toList());
        return new PageImpl<>(productDtos, pageable, products.getTotalElements());
    }
    @Override
    public List<ProductDto> getProductByAdmin() {
        List<ProductEntity> products = iProductRepository.getProductByAdmin(false);
        return products.stream().map(product ->
                iProductMapper.mapToProductDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Integer productId) {
        ProductEntity productEntity = iProductRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return iProductMapper.mapToProductDto(productEntity);
    }

    @Override
    public Page<ProductDto> searchProduct(String nameProduct, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return iProductRepository.findByNameProductContainingIgnoreCase(nameProduct,pageable)
                .map(product -> iProductMapper.mapToProductDto(product));
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        ProductEntity productEntity = iProductMapper.mapToProduct(productDto);
        if (productDto.getCategoryEntity() != null && productDto.getCategoryEntity().getId() != null) {
            CategoryEntity category = categoryRepository.findById(productDto.getCategoryEntity().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            productEntity.setCategoryEntity(category);
        }
        if (productDto.getSupplierEntity() != null && productDto.getSupplierEntity().getId() != null) {
            SupplierEntity supplier = supplierRepository.findById(productDto.getSupplierEntity().getId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found"));
            productEntity.setSupplierEntity(supplier);
        }
        if (productDto.getProducerEntity() != null && productDto.getProducerEntity().getId() != null) {
            ProducerEntity producerEntity = producerReponsitory.findById(productDto.getProducerEntity().getId())
                    .orElseThrow(() -> new RuntimeException("Producer not found"));
            productEntity.setProducerEntity(producerEntity);
        }

        productEntity.setIsDelete(false);
        productEntity.setIsActive(true);

        ProductEntity savedEntity = iProductRepository.save(productEntity);

        return iProductMapper.mapToProductDto(savedEntity);
    }

    @Override
    public ProductDto updateProduct(Integer productId, ProductDto updateProduct) {
        ProductEntity productEntity = iProductRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        productEntity.setNameProduct(updateProduct.getNameProduct());
        productEntity.setDescription(updateProduct.getDescription());
        productEntity.setPrice(updateProduct.getPrice());
        productEntity.setImageUrl(updateProduct.getImageUrl());

        if (updateProduct.getCategoryEntity() != null && updateProduct.getCategoryEntity().getId() != null) {
            CategoryEntity category = categoryRepository.findById(updateProduct.getCategoryEntity().getId())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
            productEntity.setCategoryEntity(category);
        }
        if (updateProduct.getSupplierEntity() != null && updateProduct.getSupplierEntity().getId() != null) {
            SupplierEntity supplier = supplierRepository.findById(updateProduct.getSupplierEntity().getId())
                    .orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND));
            productEntity.setSupplierEntity(supplier);
        }
        if (updateProduct.getProducerEntity() != null && updateProduct.getProducerEntity().getId() != null) {
            ProducerEntity producer = producerReponsitory.findById(updateProduct.getProducerEntity().getId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCER_NOT_FOUND));
            productEntity.setProducerEntity(producer);
        }

        ProductEntity productUpdate = iProductRepository.save(productEntity);
        return iProductMapper.mapToProductDto(productUpdate);
    }

    @Override
    public void deleteProduct(Integer productId) {
        ProductEntity productEntity = iProductRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productEntity.setIsDelete(true);
        iProductRepository.save(productEntity);
    }

    @Override
    public Long getTotalProducts() {
        return iProductRepository.count();
    }
}
