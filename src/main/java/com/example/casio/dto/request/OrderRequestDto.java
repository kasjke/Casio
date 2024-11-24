package com.example.casio.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderRequestDto(
        Long userId,
        List<OrderItemRequestDto> items) {
}
