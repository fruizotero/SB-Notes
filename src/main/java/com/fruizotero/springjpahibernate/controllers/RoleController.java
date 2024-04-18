package com.fruizotero.springjpahibernate.controllers;

import com.fruizotero.springjpahibernate.domain.dto.RoleDto;
import com.fruizotero.springjpahibernate.domain.entities.RoleEntity;
import com.fruizotero.springjpahibernate.exceptions.NotCreatedException;
import com.fruizotero.springjpahibernate.exceptions.NotUpdatedException;
import com.fruizotero.springjpahibernate.mappers.Mapper;
import com.fruizotero.springjpahibernate.services.RoleService;
import com.fruizotero.springjpahibernate.utils.ApiResponse;
import com.fruizotero.springjpahibernate.utils.ResponseMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {

    private RoleService roleService;

    private Mapper<RoleEntity, RoleDto> roleMapper;

    public RoleController(RoleService roleService, Mapper<RoleEntity, RoleDto> roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @GetMapping(path = "/roles")
    public ResponseEntity<ApiResponse<List<RoleDto>>> getRoles() {

        return ResponseEntity.ok(
                ApiResponse.<List<RoleDto>>builder()
                        .success(true)
                        .data(roleService.getAllRoles().stream().map(roleMapper::mapTo).toList())
                        .message(ResponseMessages.GET_ROLES.getMessage())
                        .build());
    }

    @GetMapping(path = "/roles/{id}")
    public ResponseEntity<ApiResponse<RoleDto>> getRole(@PathVariable int id) {

        Optional<RoleEntity> roleFound = roleService.getRole(id);

        return ResponseEntity.ok(
                ApiResponse.<RoleDto>builder()
                        .success(true)
                        .data(roleFound.map(roleMapper::mapTo).get())
                        .message(ResponseMessages.GET_ROLE.getMessage()).
                        build());


    }

    @PostMapping(path = "/roles")
    public ResponseEntity<ApiResponse<RoleDto>> createRole(@RequestBody RoleDto roleDto) {

        Optional<RoleEntity> roleEntitySaved = roleService.saveRole(roleDto);

        if (roleEntitySaved.isEmpty())
            throw new NotCreatedException(ResponseMessages.CREATE_ROLE_ERROR.getMessage());

        return ResponseEntity.ok(ApiResponse.<RoleDto>builder()
                .success(true)
                .data(roleEntitySaved.map(roleMapper::mapTo).get())
                .message(ResponseMessages.CREATE_ROLE.getMessage())
                .build());

    }

    @PutMapping(path = "/roles/{id}")
    public ResponseEntity<ApiResponse<RoleDto>> updateRole(@PathVariable int id, @RequestBody RoleDto roleDto) {

        roleDto.setId(id);
        Optional<RoleEntity> roleUpdated = roleService.updateRole(roleDto);

        if (roleUpdated.isEmpty())
            throw new NotUpdatedException(ResponseMessages.UPDATE_ROLE_ERROR.getMessage());

        return ResponseEntity.ok(ApiResponse.<RoleDto>builder()
                .success(true)
                .data(roleUpdated.map(roleMapper::mapTo).get())
                .message(ResponseMessages.UPDATE_ROLE.getMessage())
                .build());


    }

    @DeleteMapping(path = "/roles/{id}")
    public ResponseEntity<ApiResponse<String>> deleteRole(@PathVariable int id) {

        roleService.deleteRole(id);

        return new ResponseEntity<>(ApiResponse.<String>builder()
                .message(ResponseMessages.DELETE_ROLE.getMessage())
                .success(true)
                .build(),
                HttpStatus.OK);
    }


}
