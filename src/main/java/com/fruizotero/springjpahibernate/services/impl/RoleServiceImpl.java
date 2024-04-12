package com.fruizotero.springjpahibernate.services.impl;

import com.fruizotero.springjpahibernate.domain.entities.RoleEntity;
import com.fruizotero.springjpahibernate.repositories.RoleRepository;
import com.fruizotero.springjpahibernate.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void deleteRole(int id) {
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
    public Optional<RoleEntity> getRole(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<RoleEntity> saveRole(RoleEntity role) {

        return Optional.of(roleRepository.save(role));
    }

    @Override
    public Optional<RoleEntity> updateRole(RoleEntity roleEntity, int id) {

//        roleEntity.setId(id);
        Optional<RoleEntity> roleExisting = getRole(id);

        return roleExisting.map(role -> {
            Optional.ofNullable(roleEntity.getName()).ifPresent(role::setName);
            return roleRepository.save(role);
        });
    }
}
