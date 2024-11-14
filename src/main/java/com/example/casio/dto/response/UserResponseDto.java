package com.example.casio.dto.response;

public record UserResponseDto(
        String name,
        String surname,
        String email,
        String phoneNumber
) {
}
