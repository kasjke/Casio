package com.example.casio.service;

import com.example.casio.dto.response.*;

public interface CartService {

    CartResponseDto getCartByUserId(Long userId);

    CartResponseDto addItemToCart(Long userId, Long productId, int quantity);

    CartResponseDto updateItemQuantity(Long userId, Long productId, int newQuantity);

    CartResponseDto removeItemFromCart(Long userId, Long productId);

    Long calculateTotalPrice(Long userId);

    void clearCart(Long userId);
}
