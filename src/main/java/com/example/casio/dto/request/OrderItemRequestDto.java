package com.example.casio.dto.request;

import lombok.Builder;

@Builder
public record OrderItemRequestDto(
        Long productId,
        Long quantity) {
}
