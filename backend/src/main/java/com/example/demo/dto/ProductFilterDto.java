package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilterDto {

    private Double minPrice;
    private Double maxPrice;
    private List<String> suppliers;
    private List<String> categories;
    private List<String> producers;
    private String sortBy;
    private String sortDirection;

}