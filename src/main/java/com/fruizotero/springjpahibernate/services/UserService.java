package com.fruizotero.springjpahibernate.services;

import com.fruizotero.springjpahibernate.domain.dto.UserDto;
import com.fruizotero.springjpahibernate.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void deleteUser(int id);

    boolean existsUser(int id);

    boolean existsEmail(String email);

    List<UserEntity> getAllUsers();

    Optional<UserEntity> getUser(int id);

    Optional<UserEntity> saveUser(UserDto userDto);

    Optional<UserEntity> updateUser(UserDto user);

}
