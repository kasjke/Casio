package com.example.casio.service.impl;

import com.example.casio.dto.request.UserRequestDto;
import com.example.casio.dto.response.UserResponseDto;
import com.example.casio.exception.CustomNotFoundException;
import com.example.casio.mapper.UserMapper;
import com.example.casio.model.User;
import com.example.casio.repository.UserRepository;
import com.example.casio.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private static final String USER = "User";

    @Override
    @Transactional
    public void createUser(UserRequestDto userRequestDto) {

        if (userRepository.existsByEmail(userRequestDto.email())) {
            log.warn("User with email {} already exists", userRequestDto.email());
            throw new CustomNotFoundException(USER, "email", userRequestDto.email());
        }

        User user = userMapper.toEntity(userRequestDto);
        userRepository.save(user);
        log.info("User with email {} successfully created", userRequestDto.email());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User with ID {} not found", id);
                    return new CustomNotFoundException(USER, id);
                });

        log.info("User with ID {} found", id);
        return userMapper.toResponseDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User with ID {} not found for update", id);
                    return new CustomNotFoundException(USER, id);
                });

        userMapper.updateUserFromDto(userRequestDto, user);
        User updatedUserEntity = userRepository.save(user);

        log.info("User with ID {} successfully updated", id);
        return userMapper.toResponseDto(updatedUserEntity);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            log.error("User with ID {} not found for deletion", id);
            throw new CustomNotFoundException(USER, id);
        }

        userRepository.deleteById(id);
        log.info("User with ID {} successfully deleted", id);
    }
}
