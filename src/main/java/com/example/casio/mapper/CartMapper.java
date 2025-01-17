package com.example.casio.mapper;

import com.example.casio.configuration.MapperConf;
import com.example.casio.dto.CartItemDto;
import com.example.casio.dto.response.CartResponseDto;
import com.example.casio.model.Cart;
import com.example.casio.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapperConf.class)
public interface CartMapper {

    @Mapping(expression = "java(cart.calculateTotalPrice())", target = "totalPrice")
    CartResponseDto toResponseDto(Cart cart);

    CartItemDto toItemDto(CartItem cartItem);

    List<CartItemDto> toItemsDto(List<CartItem> cartItems);
}
