package com.example.casio.service.impl;

import com.example.casio.dto.request.UserRequestDto;
import com.example.casio.dto.response.UserResponseDto;
import com.example.casio.mapper.UserMapper;
import com.example.casio.model.User;
import com.example.casio.repository.UserRepository;
import com.example.casio.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByEmail(userRequestDto.email())) {
            throw new RuntimeException();
        }
        User user = userMapper.toEntity(userRequestDto);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return userMapper.toResponseDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow();
        userMapper.updateUserFromDto(userRequestDto, user);
        User updatedUserEntity = userRepository.save(user);

        return userMapper.toResponseDto(updatedUserEntity);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        userRepository.deleteById(id);
    }
}
