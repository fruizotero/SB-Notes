package com.fruizotero.springjpahibernate.services.impl;

import com.fruizotero.springjpahibernate.domain.dto.RoleDto;
import com.fruizotero.springjpahibernate.domain.entities.RoleEntity;
import com.fruizotero.springjpahibernate.exceptions.NotFoundException;
import com.fruizotero.springjpahibernate.mappers.Mapper;
import com.fruizotero.springjpahibernate.repositories.RoleRepository;
import com.fruizotero.springjpahibernate.services.RoleService;
import com.fruizotero.springjpahibernate.utils.ResponseMessages;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;
    private Mapper<RoleEntity, RoleDto> roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, Mapper<RoleEntity, RoleDto> roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public void deleteRole(int id) {

        if (!roleRepository.existsById(id))
            throw new NotFoundException(ResponseMessages.NOT_FOUND_ROLE.getMessage());

        roleRepository.deleteById(id);
    }

    @Override
    public boolean existsRole(int id) {
        return roleRepository.existsById(id);
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<RoleEntity> getRole(int id) throws RuntimeException {

        if (!roleRepository.existsById(id))
            throw new NotFoundException(ResponseMessages.NOT_FOUND_ROLE.getMessage());

        return roleRepository.findById(id);
    }

    @Override
    public Optional<RoleEntity> saveRole(RoleDto roleDto) {

        return Optional.of(roleRepository.save(roleMapper.mapFrom(roleDto)));
    }

    @Override
    public Optional<RoleEntity> updateRole(RoleDto roleDto) {

        if (!roleRepository.existsById(roleDto.getId()))
            throw new NotFoundException(ResponseMessages.NOT_FOUND_ROLE.getMessage());

        Optional<RoleEntity> roleExisting = getRole(roleDto.getId());

        return roleExisting.map(role -> {
            Optional.ofNullable(roleDto.getName()).ifPresent(role::setName);
            return roleRepository.save(role);
        });
    }

    public List<RoleEntity> getRoleEntities(List<Integer> rolesIds) {
        List<RoleEntity> roles =
                rolesIds.stream().
                        map(this::getRole)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .toList();

        return roles;
    }
}
