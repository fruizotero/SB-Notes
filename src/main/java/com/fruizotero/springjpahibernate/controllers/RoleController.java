package com.fruizotero.springjpahibernate.controllers;

import com.fruizotero.springjpahibernate.domain.dto.RoleDto;
import com.fruizotero.springjpahibernate.domain.entities.RoleEntity;
import com.fruizotero.springjpahibernate.mappers.Mapper;
import com.fruizotero.springjpahibernate.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
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
    public ResponseEntity<List<RoleDto>> getRoles() {

        return new ResponseEntity<>(roleService.getAllRoles().stream().map(roleMapper::mapTo).toList(), HttpStatus.OK);
    }

    @GetMapping(path = "/roles/{id}")
    public ResponseEntity<RoleDto> getRole(@PathVariable int id) {

        Optional<RoleEntity> roleFound = roleService.getRole(id);

        return roleFound.map(roleEntity -> {
            RoleDto roleDto = roleMapper.mapTo(roleEntity);
            return new ResponseEntity<>(roleDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping(path = "/roles")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
        RoleEntity roleEntityToSave = roleMapper.mapFrom(roleDto);
        Optional<RoleEntity> roleEntitySaved = roleService.saveRole(roleEntityToSave);

        return roleEntitySaved.map(roleEntity -> {
            RoleDto roleDtoResp = roleMapper.mapTo(roleEntity);
            return new ResponseEntity<>(roleDtoResp, HttpStatus.CREATED);
        }).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    @PutMapping(path = "/roles/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable int id, @RequestBody RoleDto roleDto) {

        RoleEntity roleToUpdate = roleMapper.mapFrom(roleDto);
        Optional<RoleEntity> roleUpdated = roleService.updateRole(roleToUpdate, id);

        return roleUpdated.map(role -> {
            RoleDto roleDtoResp = roleMapper.mapTo(role);
            return new ResponseEntity<>(roleDtoResp, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping(path = "/roles/{id}")
    public ResponseEntity deleteRole(@PathVariable int id) {

        if (!roleService.existsRole(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
