package com.fruizotero.springjpahibernate.services;

import com.fruizotero.springjpahibernate.domain.dto.NoteDto;
import com.fruizotero.springjpahibernate.domain.entities.NoteEntity;

import java.util.List;
import java.util.Optional;

public interface NoteService {

    void deleteNote(int id);

    boolean existsNote(int id);

    List<NoteEntity> getAllNotes();

    List<NoteEntity> getAllNotesUser(int userId);

    Optional<NoteEntity> getNote(int id);

    Optional<NoteEntity> createNote(NoteDto noteDto);

    Optional<NoteEntity> updateNote(NoteDto noteDto);

}
