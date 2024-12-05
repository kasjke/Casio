package com.example.casio.controller;

import com.example.casio.dto.request.OrderRequestDto;
import com.example.casio.dto.response.OrderResponseDto;
import com.example.casio.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok(orderService.createOrder(orderRequestDto));
    }

    @GetMapping("/id")
    ResponseEntity<OrderResponseDto> getOrderById(Long userId) {
        return ResponseEntity.ok(orderService.getOrderById(userId));
    }

    @GetMapping
    ResponseEntity<List<OrderResponseDto>> getOrdersById(Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @PutMapping("/id")
    ResponseEntity<Void> cancelOrder(Long userId) {
        orderService.cancelOrder(userId);
        return ResponseEntity.noContent().build();
    }

}
