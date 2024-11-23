package com.example.casio.service.impl;

import com.example.casio.dto.response.CartResponseDto;
import com.example.casio.repository.CartRepository;
import com.example.casio.repository.ProductRepository;
import com.example.casio.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    @Override
    public CartResponseDto getCartByUserId(Long userId) {
        return null;
    }

    @Override
    public CartResponseDto addItemToCart(Long userId, Long productId, int quantity) {
        return null;
    }

    @Override
    public CartResponseDto updateItemQuantity(Long userId, Long productId, int newQuantity) {
        return null;
    }

    @Override
    public Long getTotalPrice(Long userId) {
        return 0L;
    }

    @Override
    public CartResponseDto removeItemFromCart(Long userId, Long productId) {
        return null;
    }

    @Override
    public void clearCart(Long userId) {

    }
}
