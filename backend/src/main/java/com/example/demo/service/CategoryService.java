package com.example.demo.service;
import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.mapper.ICategoryMapper;
import com.example.demo.reponsitories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class CategoryService {
    CategoryRepository categoryRepository;
    ICategoryMapper categoryMapper;
    public List<CategoryDto> getAllCategory() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return categoryEntityList.stream().map(categoryMapper::mapToCategoryDto).collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(Integer id) {
        return categoryMapper.mapToCategoryDto(categoryRepository.findById(id).orElse(null));
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryMapper.mapToCategory(categoryDto);
        return categoryMapper.mapToCategoryDto(categoryRepository.save(categoryEntity));
    }

    public CategoryDto updateCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryMapper.mapToCategory(categoryDto);
        return categoryMapper.mapToCategoryDto(categoryRepository.save(categoryEntity));
    }

    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }

    public boolean checkCategoryExistByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
