package com.example.casio.mapper;

import com.example.casio.dto.request.ProductRequestDto;
import com.example.casio.dto.response.ProductResponseDto;
import com.example.casio.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    public Product toModel(ProductRequestDto productRequestDto);

    public ProductResponseDto toResponseDto(Product product);

}
