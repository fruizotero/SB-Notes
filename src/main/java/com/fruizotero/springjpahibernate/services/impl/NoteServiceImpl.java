package com.fruizotero.springjpahibernate.services.impl;

import com.fruizotero.springjpahibernate.domain.dto.NoteDto;
import com.fruizotero.springjpahibernate.domain.entities.NoteEntity;
import com.fruizotero.springjpahibernate.mappers.Mapper;
import com.fruizotero.springjpahibernate.repositories.NoteRepository;
import com.fruizotero.springjpahibernate.services.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;
    private Mapper<NoteEntity, NoteDto> noteMapper;

    public NoteServiceImpl(NoteRepository noteRepository, Mapper<NoteEntity, NoteDto> noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }


    @Override
    public void deleteNote(int id) {

        noteRepository.deleteById(id);

    }

    @Override
    public boolean existsNote(int id) {
        return noteRepository.existsById(id);
    }

    @Override
    public List<NoteEntity> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public List<NoteEntity> getAllNotesUser(int userId) {
        return noteRepository.findByUserIdId(userId);
    }

    @Override
    public Optional<NoteEntity> getNote(int id) {
        return noteRepository.findById(id);
    }

    @Override
    public Optional<NoteEntity> createNote(NoteDto noteDto) {
        return Optional.of(noteRepository.save(noteMapper.mapFrom(noteDto)));
    }

    @Override
    public Optional<NoteEntity> updateNote(NoteDto noteDto) throws RuntimeException {

        if (!noteRepository.existsById(noteDto.getId())) {
            throw new RuntimeException("note not found");
        }

        Optional<NoteEntity> noteEntityToUpdate = getNote(noteDto.getId());


        return noteEntityToUpdate.map(note -> {
            if (Optional.ofNullable(noteDto.getTitle()).isPresent()) {
                note.setTitle(noteDto.getTitle());
            }

            if (Optional.ofNullable(noteDto.getContent()).isPresent()) {
                note.setContent(noteDto.getContent());
            }

            return noteRepository.save(note);

        });
    }
}
