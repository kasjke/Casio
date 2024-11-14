package com.example.casio.mapper;

import com.example.casio.configuration.MapperConf;
import com.example.casio.dto.request.UserRequestDto;
import com.example.casio.dto.response.UserResponseDto;
import com.example.casio.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", config = MapperConf.class)
public interface UserMapper {
    UserResponseDto toResponseDto(User user);
    User toEntity(UserRequestDto userRequestDto);
    void updateUserFromDto(UserRequestDto userRequestDto, @MappingTarget User user);
}
