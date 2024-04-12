package com.fruizotero.springjpahibernate.services;

import com.fruizotero.springjpahibernate.domain.entities.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    void deleteRole(int id);

    boolean existsRole(int id);

    List<RoleEntity> getAllRoles();

    Optional<RoleEntity> getRole(int id);

    Optional<RoleEntity> saveRole(RoleEntity role);

    Optional<RoleEntity> updateRole(RoleEntity role, int id);

}
