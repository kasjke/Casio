package com.example.casio.dto.request;

import lombok.Builder;

@Builder
public record ProductRequestDto(String name, Long price, String description) {
}
