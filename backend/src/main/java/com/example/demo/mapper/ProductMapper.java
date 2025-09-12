package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.ProductEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto mapToProductDto(ProductEntity product);
    ProductEntity mapToProduct(ProductDto product);

}
