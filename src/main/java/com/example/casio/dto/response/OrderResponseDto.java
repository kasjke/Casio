package com.example.casio.dto.response;

import com.example.casio.enums.OrderStatus;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record OrderResponseDto(
        Long id,
        Long userId,
        List<OrderItemResponseDto> items,
        OrderStatus status,
        Long totalPrice,
        Date createdAt) {
}
