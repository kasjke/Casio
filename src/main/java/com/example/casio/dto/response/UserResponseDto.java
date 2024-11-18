package com.example.casio.dto.response;

public record UserResponseDto(
        String name,
        String surname,
        String nickname,
        String email,
        String phoneNumber
) {
}
