package com.example.casio.controller;

import com.example.casio.dto.request.UserRequestDto;
import com.example.casio.dto.response.UserResponseDto;
import com.example.casio.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final String BY_ID = "/{id}";
    @PostMapping
    public  ResponseEntity<Void> createUser( @RequestBody @Valid UserRequestDto user) {
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }



    @GetMapping(BY_ID)
    public ResponseEntity<UserResponseDto> findById( @PathVariable Long id) {
        UserResponseDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping(BY_ID)
    public UserResponseDto updateUser(@PathVariable Long id,@Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUser(id, userRequestDto);
    }

    @DeleteMapping(BY_ID)
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
