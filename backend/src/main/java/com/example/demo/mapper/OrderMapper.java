package com.example.demo.mapper;

import com.example.demo.dto.response.OrderResponse;
import com.example.demo.entity.OrderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toOrderResponse(OrderEntity orderEntity);
}
