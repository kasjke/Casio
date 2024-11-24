package com.example.casio.service.impl;

import com.example.casio.dto.request.OrderRequestDto;
import com.example.casio.dto.response.OrderResponseDto;
import com.example.casio.enums.OrderStatus;
import com.example.casio.mapper.OrderMapper;
import com.example.casio.model.Order;
import com.example.casio.model.OrderItem;
import com.example.casio.model.Product;
import com.example.casio.repository.OrderRepository;
import com.example.casio.repository.ProductRepository;
import com.example.casio.service.CartService;
import com.example.casio.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Order order = orderMapper.toEntity(orderRequestDto);

        List<OrderItem> items = orderRequestDto.items().stream()
                .map(orderItemDto -> {
                    Product product = productRepository.findById(orderItemDto.productId())
                            .orElseThrow(() -> new EntityNotFoundException("Product not found"));
                    return OrderItem.builder()
                            .order(order)
                            .product(product)
                            .quantity(orderItemDto.quantity())
                            .price(product.getPrice())
                            .build();
                })
                .toList();

        order.setItems(items);
        order.setCreatedAt(new Date());
        order.calculateTotalPrice();
        order.setStatus(OrderStatus.IN_PROGRESS);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toResponseDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(orderMapper::toResponseDto)
                .toList();
    }

    @Override
    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        return orderMapper.toResponseDto(order);
    }

    @Override
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
    }
}
