package com.example.casio.dto.response;

import com.example.casio.dto.CartItemDto;

import java.util.List;

public record CartResponseDto(
        Long id,
        List<CartItemDto> items,
        Long totalPrice
) {
}
