package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductFilterDto;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {

    Page<ProductDto> getAllProduct(Pageable pageable, ProductFilterDto filter);
    ProductDto getProductById(Integer productId);
    Page<ProductDto> searchProduct(String nameProduct, int page, int size);
    List<ProductDto> getProductByAdmin();
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(Integer productId, ProductDto updateProduct);

    void deleteProduct(Integer productId);
    Long getTotalProducts();
}
