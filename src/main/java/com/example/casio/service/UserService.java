package com.example.casio.service;

import com.example.casio.dto.request.UserRequestDto;
import com.example.casio.dto.response.UserResponseDto;

public interface UserService {
    void createUser(UserRequestDto user);

    UserResponseDto findById(Long id);

    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    void deleteUser(Long id);
}
