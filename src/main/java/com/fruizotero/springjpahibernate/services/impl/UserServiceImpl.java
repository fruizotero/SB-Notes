package com.fruizotero.springjpahibernate.services.impl;

import com.fruizotero.springjpahibernate.domain.dto.UserDto;
import com.fruizotero.springjpahibernate.domain.entities.RoleEntity;
import com.fruizotero.springjpahibernate.domain.entities.UserEntity;
import com.fruizotero.springjpahibernate.exceptions.NotFoundException;
import com.fruizotero.springjpahibernate.exceptions.NotValidDataException;
import com.fruizotero.springjpahibernate.mappers.Mapper;
import com.fruizotero.springjpahibernate.repositories.UserRepository;
import com.fruizotero.springjpahibernate.services.RoleService;
import com.fruizotero.springjpahibernate.services.UserService;
import com.fruizotero.springjpahibernate.utils.ResponseMessages;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleService roleService;
    private Mapper<UserEntity, UserDto> userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, Mapper<UserEntity, UserDto> userMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userMapper = userMapper;
    }


    @Override
    public void deleteUser(int id) {

        validateUserId(id);

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

        validateUserId(id);

        return userRepository.findById(id);

    }

    @Override
    @Transactional
    public Optional<UserEntity> saveUser(UserDto userDto) {

        if (userRepository.existsByEmail(userDto.getEmail()))
            throw new NotValidDataException(
                    ResponseMessages.INVALID_EMAIL.getMessage(),
                    new HashMap<>() {{
                        put("email", userDto.getEmail());
                    }});

        validateRoles(userDto);

        UserEntity userToCreate = userMapper.mapFrom(userDto);
        List<RoleEntity> roles = roleService.getRoleEntities(userDto.getRoles());
        userToCreate.setRoles(roles);


        return Optional.of(userRepository.save(userToCreate));
    }

    @Override
    @Transactional
    public Optional<UserEntity> updateUser(UserDto userToUpdateDto) {

        validateUserId(userToUpdateDto.getId());

        Optional<UserEntity> userToUpdateEntity = getUser(userToUpdateDto.getId());

        if (!userToUpdateEntity.get().getEmail().equalsIgnoreCase(userToUpdateDto.getEmail()) && existsEmail(userToUpdateDto.getEmail()))
            throw new NotValidDataException(ResponseMessages.INVALID_EMAIL.getMessage(), new HashMap<>() {{
                put("email", userToUpdateDto.getEmail());
            }});

        validateRoles(userToUpdateDto);

        return userToUpdateEntity.map(user -> {

            if (Optional.ofNullable(userToUpdateDto.getEmail()).isPresent())
                user.setEmail(userToUpdateDto.getEmail());

            //TODO:: Tenemos que hashear la contraseña
            if (Optional.ofNullable(userToUpdateDto.getPassword()).isPresent())
                user.setPassword(userToUpdateDto.getPassword());

            if (Optional.ofNullable(userToUpdateDto.getRoles()).isPresent()) {
                user.getRoles().clear();
                List<RoleEntity> roleEntities = roleService.getRoleEntities(userToUpdateDto.getRoles());

                user.getRoles().addAll(roleEntities);
            }
            return userRepository.save(user);

        });
    }

    private void validateUserId(int userToUpdateDto) {
        if (!userRepository.existsById(userToUpdateDto))
            throw new NotFoundException(ResponseMessages.NOT_FOUND_USER.getMessage());
    }

    private void validateRoles(UserDto userToUpdateDto) {
        userToUpdateDto.getRoles()
                .forEach(rolId -> {
                    if (!roleService.existsRole(rolId))
                        throw new NotValidDataException(
                                ResponseMessages.INVALID_ROL.getMessage(),
                                new HashMap<>() {{
                                    put("roles", userToUpdateDto.getRoles().toString());
                                }});
                });
    }
}
