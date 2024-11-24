package com.example.casio.service;

import com.example.casio.dto.response.*;

public interface CartService {

    CartResponseDto getCartByUserId(Long userId);

    CartResponseDto addItemToCart(Long userId, Long productId, long quantity);

    CartResponseDto updateItemQuantity(Long userId, Long productId, long newQuantity);

    CartResponseDto removeItemFromCart(Long userId, Long productId);

    Long getTotalPrice(Long userId);

    void clearCart(Long userId);
}
