package com.fruizotero.springjpahibernate.repositories;

import com.fruizotero.springjpahibernate.domain.entities.RoleEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends ListCrudRepository<RoleEntity, Integer> {

}
