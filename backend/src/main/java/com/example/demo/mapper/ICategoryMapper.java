package com.example.demo.mapper;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {
    CategoryDto mapToCategoryDto(CategoryEntity category);
    CategoryEntity mapToCategory(CategoryDto category);
}
