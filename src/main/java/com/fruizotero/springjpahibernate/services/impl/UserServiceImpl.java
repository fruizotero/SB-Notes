package com.fruizotero.springjpahibernate.services.impl;

import com.fruizotero.springjpahibernate.domain.dto.UserDto;
import com.fruizotero.springjpahibernate.domain.entities.RoleEntity;
import com.fruizotero.springjpahibernate.domain.entities.UserEntity;
import com.fruizotero.springjpahibernate.repositories.UserRepository;
import com.fruizotero.springjpahibernate.services.RoleService;
import com.fruizotero.springjpahibernate.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    @Override
    public void deleteUser(int id) {

        userRepository.deleteById(id);

    }

    @Override
    public boolean existsUser(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean existsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUser(int id) {

        if (!userRepository.existsById(id))
            return Optional.empty();

        return userRepository.findById(id);

    }

    @Override
    public Optional<UserEntity> saveUser(UserEntity user) {

        return Optional.of(userRepository.save(user));
    }

    @Override
    @Transactional
    public Optional<UserEntity> updateUser(UserDto userToUpdateDto) throws RuntimeException {
        //
        if (!userRepository.existsById(userToUpdateDto.getId()))
            throw new RuntimeException("user not found");

        Optional<UserEntity> userToUpdateEntity = getUser(userToUpdateDto.getId());

        if (!userToUpdateEntity.get().getEmail().equalsIgnoreCase(userToUpdateDto.getEmail()) && existsEmail(userToUpdateDto.getEmail()))
           throw new RuntimeException("Invalid email");



        return userToUpdateEntity.map(user -> {

            if (Optional.ofNullable(userToUpdateDto.getEmail()).isPresent())
                user.setEmail(userToUpdateDto.getEmail());
//            Optional.ofNullable(userUpdate.getEmail()).ifPresent(user::setEmail);
            //TODO:: Tenemos que hashear la contraseña
            if (Optional.ofNullable(userToUpdateDto.getPassword()).isPresent())
                user.setPassword(userToUpdateDto.getPassword());
//            Optional.ofNullable(userUpdate.getPassword()).ifPresent(user::setPassword);

//            if (Optional.ofNullable(userToUpdateDto.getRolesIds()).isPresent()) {
            if (Optional.ofNullable(userToUpdateDto.getRoles()).isPresent()) {
                user.getRoles().clear();
                List<RoleEntity> roleEntities = roleService.getRoleEntities(userToUpdateDto.getRoles());
//                List<RoleEntity> roleEntities = roleService.getRoleEntities(userToUpdateDto.getRolesIds());
                user.getRoles().addAll(roleEntities);
            }
            return userRepository.save(user);

        });
    }
}
