package com.example.casio.mapper;

import com.example.casio.configuration.MapperConf;
import com.example.casio.dto.request.OrderItemRequestDto;
import com.example.casio.dto.request.OrderRequestDto;
import com.example.casio.dto.response.OrderItemResponseDto;
import com.example.casio.dto.response.OrderResponseDto;
import com.example.casio.model.Order;
import com.example.casio.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConf.class)
public interface OrderMapper {

    @Mapping(target = "user.id", source = "userId")
    Order toEntity(OrderRequestDto orderRequestDto);

    @Mapping(target = "userId", source = "user.id")
    OrderResponseDto toResponseDto(Order order);

    @Mapping(target = "product.id", source = "productId" )
    OrderItem toOrderItem(OrderItemRequestDto orderItemRequestDto);

    @Mapping(target = "productId", source = "product.id")
    OrderItemResponseDto toOrderItemResponseDto(OrderItem orderItem);
}
