package com.example.casio.dto;

import com.example.casio.model.Product;

public record CartItemDto(
        Long cartId,
        Product product,
        Long quantity,
        Long price) {
}
