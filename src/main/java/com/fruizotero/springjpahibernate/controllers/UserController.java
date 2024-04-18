package com.fruizotero.springjpahibernate.controllers;


import com.fruizotero.springjpahibernate.domain.dto.UserDto;
import com.fruizotero.springjpahibernate.domain.entities.UserEntity;
import com.fruizotero.springjpahibernate.exceptions.NotCreatedException;
import com.fruizotero.springjpahibernate.exceptions.NotUpdatedException;
import com.fruizotero.springjpahibernate.mappers.Mapper;
import com.fruizotero.springjpahibernate.services.RoleService;
import com.fruizotero.springjpahibernate.services.UserService;
import com.fruizotero.springjpahibernate.utils.ApiResponse;
import com.fruizotero.springjpahibernate.utils.ResponseMessages;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    private UserService userService;
    private Mapper<UserEntity, UserDto> userMapper;

    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper, RoleService roleService) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @GetMapping(path = "/users")
    public ResponseEntity<ApiResponse<List<UserDto>>> getUsers() {

        return ResponseEntity.ok(
                ApiResponse.<List<UserDto>>builder()
                        .success(true)
                        .data(userService.getAllUsers()
                                .stream()
                                .map(userMapper::mapTo)
                                .toList())
                        .message(ResponseMessages.GET_USERS.getMessage())
                        .build());

    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUser(@PathVariable int id) {

        Optional<UserEntity> userFound = userService.getUser(id);

        return ResponseEntity.ok(
                ApiResponse.<UserDto>builder()
                        .success(true)
                        .data(userFound.map(userMapper::mapTo).get())
                        .message(ResponseMessages.GET_USER.getMessage())
                        .build()
        );


    }

    @PostMapping(path = "/users")
    public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody UserDto userDto) {

        Optional<UserEntity> userCreated = userService.saveUser(userDto);

        if (userCreated.isEmpty())
            throw new NotCreatedException(ResponseMessages.CREATE_USER_ERROR.getMessage());

        return ResponseEntity.ok(
                ApiResponse.<UserDto>builder()
                        .success(true)
                        .data(userCreated.map(userMapper::mapTo).get())
                        .message(ResponseMessages.CREATE_USER.getMessage())
                        .build()
        );


    }


    @PutMapping(path = "/users/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {

        userDto.setId(id);
        Optional<UserEntity> userUpdated = userService.updateUser(userDto);
        if (userUpdated.isEmpty())
            throw new NotUpdatedException(ResponseMessages.UPDATE_USER_ERROR.getMessage());

        return ResponseEntity.ok(
                ApiResponse.<UserDto>builder()
                        .success(true)
                        .data(userUpdated.map(userMapper::mapTo).get())
                        .message(ResponseMessages.UPDATE_USER.getMessage())
                        .build()
        );

    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable int id) {

        userService.deleteUser(id);
        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(false)
                        .message(ResponseMessages.DELETE_USER.getMessage())
                        .build());
    }

}
