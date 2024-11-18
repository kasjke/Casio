package com.example.casio.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @Size(min = 2, message = "Name must contain at least 2 characters")
        @Pattern(regexp = "^[А-Яа-яA-Za-z]+$", message = "Name must contain only Ukrainian or Latin characters")
        String name,
        @Size(min = 1, message = "Surname must contain at least 1 character")
        @Pattern(regexp = "^[А-Яа-яA-Za-z]+$", message = "Surname must contain only Ukrainian or Latin characters")
        String surname,
        @Size(min = 4, message = "Nickname must contain at least 4 characters")
        @Pattern(regexp = "^[А-Яа-яA-Za-z]+$", message = "Nickname must contain only Ukrainian or Latin characters")
        String nickname,
        @Email(message = "Invalid email format")
        String email,
        @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number")
        String phoneNumber,
        @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "Password must contain at least one letter and one number")
        String password
) {}