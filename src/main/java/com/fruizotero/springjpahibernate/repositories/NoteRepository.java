package com.fruizotero.springjpahibernate.repositories;

import com.fruizotero.springjpahibernate.domain.entities.NoteEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends ListCrudRepository<NoteEntity, Integer> {

    List<NoteEntity> findByUserIdId(Integer userId);

}
