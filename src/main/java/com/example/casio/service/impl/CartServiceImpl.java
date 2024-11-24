package com.example.casio.service.impl;

import com.example.casio.dto.response.CartResponseDto;
import com.example.casio.exception.CustomNotFoundException;
import com.example.casio.mapper.CartMapper;
import com.example.casio.model.Cart;
import com.example.casio.model.CartItem;
import com.example.casio.model.Product;
import com.example.casio.model.User;
import com.example.casio.repository.CartRepository;
import com.example.casio.repository.ProductRepository;
import com.example.casio.repository.UserRepository;
import com.example.casio.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public CartResponseDto getCartByUserId(Long userId) {
        final Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(EntityNotFoundException::new);
        return cartMapper.toResponseDto(cart);
    }

    @Override
    public CartResponseDto addItemToCart(Long userId, Long productId, long quantity) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomNotFoundException("user", userId));

        Cart cart = cartRepository.findByUserId(userId)
                .orElse(Cart.builder()
                        .user(user)
                        .build());

        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found for id: " + productId));

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseGet(() -> {
                    CartItem newItem = CartItem.builder()
                            .cart(cart)
                            .product(product)
                            .quantity(0L)
                            .build();
                    cart.getItems().add(newItem);
                    return newItem;
                });

        cartItem.setQuantity(cartItem.getQuantity() + quantity);
        cartRepository.save(cart);

        return cartMapper.toResponseDto(cart);
    }

    @Override
    public CartResponseDto updateItemQuantity(Long userId, Long productId, long newQuantity) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for userId: " + userId));

        CartItem cartItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Product not found in cart"));

        cartItem.setQuantity(newQuantity);
        Cart savedCart = cartRepository.save(cart);

        return cartMapper.toResponseDto(savedCart);
    }

    @Override
    public Long getTotalPrice(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(EntityNotFoundException::new);
        return cart.getTotalPrice();
    }

    @Override
    public CartResponseDto removeItemFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(EntityNotFoundException::new);
        cart.getItems().removeIf(item -> item.getId().equals(productId));
        final Cart savedCart = cartRepository.save(cart);
        return cartMapper.toResponseDto(savedCart);
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(EntityNotFoundException::new);
        cart.getItems().clear();
        cart.setTotalPrice(0L);
        cartRepository.save(cart);
    }
}
