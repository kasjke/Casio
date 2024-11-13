package com.example.casio.mapper;

import com.example.casio.dto.request.ProductRequestDto;
import com.example.casio.dto.response.ProductResponseDto;
import com.example.casio.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toModel(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.name())
                .price(productRequestDto.price())
                .description(productRequestDto.description())
                .build();
    }

    public ProductResponseDto toResponseDto(Product product){
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }
}
