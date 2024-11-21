package com.example.casio.controller;

import com.example.casio.dto.response.CartResponseDto;
import com.example.casio.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<CartResponseDto> getCart(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    @PostMapping("/items")
    public ResponseEntity<CartResponseDto> addItemToCart(@RequestParam Long userId,
                                                         @RequestParam Long productId,
                                                         @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addItemToCart(userId, productId, quantity));
    }

    @PutMapping("/items/{productId}")
    public ResponseEntity<CartResponseDto> updateItemQuantity(@RequestParam Long userId,
                                                              @PathVariable Long productId,
                                                              @RequestParam int newQuantity) {
        return ResponseEntity.ok(cartService.updateItemQuantity(userId, productId, newQuantity));
    }

    @DeleteMapping("/items/{productId}")
    public ResponseEntity<CartResponseDto> removeItemFromCart(@RequestParam Long userId,
                                                              @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeItemFromCart(userId, productId));
    }

    @GetMapping("/total")
    public ResponseEntity<Long> getTotalPrice(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.calculateTotalPrice(userId));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }

}
