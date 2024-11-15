package com.example.casio.controller;

import com.example.casio.dto.request.UserRequestDto;
import com.example.casio.dto.response.UserResponseDto;
import com.example.casio.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User API", description = "API for users management")
public class UserController {

    private final UserService userService;
    private final String BY_ID = "/{id}";

    @Operation(summary = "Create a new user", description = "Creates a new user with the provided data")
    @ApiResponse(responseCode = "202", description = "User created successfully")
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        userService.createUser(userRequestDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Find user by ID", description = "Retrieves a user by their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping(BY_ID)
    public ResponseEntity<UserResponseDto> findById(@Parameter(description = "ID of the user to be retrieved") @PathVariable Long id) {
        UserResponseDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Update user by ID", description = "Updates an existing user with the provided data")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping(BY_ID)
    public UserResponseDto updateUser(@Parameter(description = "ID of the user to be updated") @PathVariable Long id,
                                      @RequestBody UserRequestDto userRequestDto) {
        return userService.updateUser(id, userRequestDto);
    }

    @Operation(summary = "Delete user by ID", description = "Deletes an existing user by their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping(BY_ID)
    public ResponseEntity<Void> deleteUserById(@Parameter(description = "ID of the user to be deleted") @PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
