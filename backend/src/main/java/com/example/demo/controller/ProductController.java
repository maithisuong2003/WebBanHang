package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductFilterDto;
import com.example.demo.dto.response.ApiResponse;
import com.example.demo.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private IProductService iProductService;
    @GetMapping("/all")
    public ApiResponse<List<ProductDto>> getProductByAdmin() {
        List<ProductDto> productDtos = iProductService.getProductByAdmin();
        return ApiResponse.<List<ProductDto>>builder()
                .code(200)
                .message("Success")
                .result(productDtos)
                .build();
    }
    @GetMapping("/{id}")
    public ApiResponse<ProductDto> getProductById(@PathVariable Integer id){
        return ApiResponse.<ProductDto>builder()
                .code(200)
                .message("Success")
                .result(iProductService.getProductById(id))
                .build();
    }
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('PRODUCTS_POST')")
    public ApiResponse<ProductDto> create(@RequestBody ProductDto productDto){
        return ApiResponse.<ProductDto>builder()
                .code(201)
                .message("Created")
                .result(iProductService.createProduct(productDto))
                .build();
    }
    @PutMapping("/update/{id}")
    public ApiResponse<ProductDto> update(@PathVariable  Integer id,@RequestBody ProductDto productDto){
        return ApiResponse.<ProductDto>builder()
                .code(200)
                .message("Success")
                .result(iProductService.updateProduct(id,productDto))
                .build();
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<ProductDto> delete(@PathVariable Integer id){
        iProductService.deleteProduct(id);
        return ApiResponse.<ProductDto>builder()
                .code(200)
                .message("Removed")
                .build();
    }
    @GetMapping("/total")
    @PreAuthorize("hasAuthority('PRODUCTS_GET')")
    public ApiResponse<Long> getTotalProduct(){
        return ApiResponse.<Long>builder()
                .message("Success")
                .result(iProductService.getTotalProducts())
                .build();
    }
    @GetMapping("/search/{nameProduct}")
    public ApiResponse<Page<ProductDto>> searchProduct(
            @PathVariable("nameProduct") String nameProduct,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        Page<ProductDto> productDtos = iProductService.searchProduct(nameProduct, page, size);
        return ApiResponse.<Page<ProductDto>>builder()
                .message("Success")
                .result(productDtos)
                .build();
    }
    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) List<String> suppliers,
            @RequestParam(required = false) List<String> categories,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        ProductFilterDto filter = new ProductFilterDto();
        filter.setMinPrice(minPrice);
        filter.setMaxPrice(maxPrice);
        filter.setSuppliers(suppliers);
        filter.setCategories(categories);
        filter.setSortBy(sortBy);
        filter.setSortDirection(sortDirection);
        Sort sort = sortDirection.equalsIgnoreCase("ASC") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return iProductService.getAllProduct(pageable, filter);
    }
}
