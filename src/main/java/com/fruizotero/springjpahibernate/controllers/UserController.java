package com.fruizotero.springjpahibernate.controllers;


import com.fruizotero.springjpahibernate.domain.dto.RoleDto;
import com.fruizotero.springjpahibernate.domain.dto.UserDto;
import com.fruizotero.springjpahibernate.domain.entities.RoleEntity;
import com.fruizotero.springjpahibernate.domain.entities.UserEntity;
import com.fruizotero.springjpahibernate.mappers.Mapper;
import com.fruizotero.springjpahibernate.repositories.UserRepository;
import com.fruizotero.springjpahibernate.services.RoleService;
import com.fruizotero.springjpahibernate.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class UserController {


    private UserService userService;
    private RoleService roleService;
    private Mapper<UserEntity, UserDto> userMapper;

    public UserController(UserService userService, Mapper<UserEntity, UserDto> userMapper, RoleService roleService, Mapper<RoleEntity, RoleDto> roleMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleService = roleService;
    }


    @GetMapping(path = "/users")
    public ResponseEntity<List<UserDto>> getUsers() {

        return new ResponseEntity<>(
                userService.getAllUsers()
                        .stream()
                        .map(userMapper::mapTo)
                        .toList(),
                HttpStatus.OK);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {

        if (!userService.existsUser(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Optional<UserEntity> userFound = userService.getUser(id);

        return userFound.map(user -> new ResponseEntity<>(userMapper.mapTo(user), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    @PostMapping(path = "/users")
    @Transactional
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {

        if (userService.existsEmail(userDto.getEmail()))
            return new ResponseEntity<>("Duplicate email", HttpStatus.CONFLICT);

        UserEntity userToCreate = userMapper.mapFrom(userDto);

        //TODO::refactorizar
        List<RoleEntity> roles = roleService.getRoleEntities(userDto.getRoles());
//        List<RoleEntity> roles = roleService.getRoleEntities(userDto.getRolesIds());

        userToCreate.setRoles(roles);

        Optional<UserEntity> userCreated = userService.saveUser(userToCreate);

        return userCreated.map(user -> new ResponseEntity<>(userMapper.mapTo(user), HttpStatus.CREATED)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }


    @PutMapping(path = "/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDto userDto) {

        userDto.setId(id);
        Optional<UserEntity> userUpdated = userService.updateUser(userDto);

        return userUpdated.map(user ->
                new ResponseEntity<>(userMapper.mapTo(user), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {

        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
