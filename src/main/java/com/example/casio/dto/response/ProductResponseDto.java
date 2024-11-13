package com.example.casio.dto.response;

import lombok.Builder;

@Builder
public record ProductResponseDto(String name, Long price, String description) {
}
