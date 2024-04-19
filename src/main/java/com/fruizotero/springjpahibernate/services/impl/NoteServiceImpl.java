package com.fruizotero.springjpahibernate.services.impl;

import com.fruizotero.springjpahibernate.domain.dto.NoteDto;
import com.fruizotero.springjpahibernate.domain.entities.NoteEntity;
import com.fruizotero.springjpahibernate.exceptions.NotFoundException;
import com.fruizotero.springjpahibernate.exceptions.NotValidDataException;
import com.fruizotero.springjpahibernate.mappers.Mapper;
import com.fruizotero.springjpahibernate.repositories.NoteRepository;
import com.fruizotero.springjpahibernate.services.NoteService;
import com.fruizotero.springjpahibernate.services.UserService;
import com.fruizotero.springjpahibernate.utils.ResponseMessages;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;
    private Mapper<NoteEntity, NoteDto> noteMapper;
    private UserService userService;

    public NoteServiceImpl(NoteRepository noteRepository, Mapper<NoteEntity, NoteDto> noteMapper, UserService userService) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
        this.userService = userService;
    }


    @Override
    public void deleteNote(int id) {

        validateNoteId(id);
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
       if(!userService.existsUser(userId))
           throw new NotFoundException(ResponseMessages.NOT_FOUND_USER.getMessage());

        return noteRepository.findByUserIdId(userId);
    }

    @Override
    public Optional<NoteEntity> getNote(int id) {
        validateNoteId(id);
        return noteRepository.findById(id);
    }

    @Override
    public Optional<NoteEntity> createNote(NoteDto noteDto) {

        validateUserId(noteDto.getUserId());

        return Optional.of(noteRepository.save(noteMapper.mapFrom(noteDto)));
    }


    @Override
    public Optional<NoteEntity> updateNote(NoteDto noteDto) throws RuntimeException {

        validateNoteId(noteDto.getId());

//        validateUserId(noteDto.getUserId());

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

    private void validateNoteId(int noteId) {
        if (!noteRepository.existsById(noteId))
            throw new NotFoundException(ResponseMessages.NOT_FOUND_NOTE.getMessage());
    }

    private void validateUserId(int userId) {
        if (!userService.existsUser(userId))
            throw new NotValidDataException(ResponseMessages.INVALID_USER_ID.getMessage(), new HashMap<>() {{
                put("user_id", String.valueOf(userId));
            }});
    }
}
