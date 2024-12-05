package com.example.casio.dto.response;

import lombok.Builder;

@Builder
public record OrderItemResponseDto(
        Long productId,
        int quantity,
        Long price) {
}
