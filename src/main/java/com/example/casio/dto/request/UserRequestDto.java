package com.example.casio.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        String name,
        String surname,
        @Email(message = "Invalid email format")
        String email,
        @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
        String phoneNumber,
        @Size(min = 3, max = 30, message = "Password must be between 3 and 30 characters")
        String password
) {}