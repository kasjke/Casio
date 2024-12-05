package com.example.casio.service;

import com.example.casio.dto.request.OrderRequestDto;
import com.example.casio.dto.response.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    List<OrderResponseDto> getOrdersByUserId(Long userId);

    OrderResponseDto getOrderById(Long id);

    void cancelOrder(Long id);

}
