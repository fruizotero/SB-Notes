package com.fruizotero.springjpahibernate.repositories;

import com.fruizotero.springjpahibernate.domain.entities.UserEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<UserEntity, Integer> {

    boolean existsByEmail(String email);

}
