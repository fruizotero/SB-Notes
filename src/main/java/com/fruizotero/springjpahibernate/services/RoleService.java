package com.fruizotero.springjpahibernate.services;

import com.fruizotero.springjpahibernate.domain.dto.RoleDto;
import com.fruizotero.springjpahibernate.domain.entities.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    void deleteRole(int id);

    boolean existsRole(int id);

    List<RoleEntity> getAllRoles();

    Optional<RoleEntity> getRole(int id);

    Optional<RoleEntity> saveRole(RoleDto roleDto);

    Optional<RoleEntity> updateRole(RoleDto roleDto);

    List<RoleEntity> getRoleEntities(List<Integer> rolesIds);
}
