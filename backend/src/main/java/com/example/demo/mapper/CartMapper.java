package com.example.demo.mapper;

import com.example.demo.dto.response.CartItemResponse;
import com.example.demo.entity.CartEntity;
import com.example.demo.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "userId", source = "cartEntity.userEntity.id")
    @Mapping(target = "cartId", source = "cartEntity.id")
    @Mapping(target = "productId", source = "productEntity.id")
    CartItemResponse mapToCartItemDto(CartItemEntity cartItemEntity);
    CartItemEntity mapToCartItemEntity(CartItemResponse cartItemResponse);

}
