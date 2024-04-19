package com.fruizotero.springjpahibernate.controllers;

import com.fruizotero.springjpahibernate.domain.dto.NoteDto;
import com.fruizotero.springjpahibernate.domain.entities.NoteEntity;
import com.fruizotero.springjpahibernate.exceptions.NotCreatedException;
import com.fruizotero.springjpahibernate.exceptions.NotUpdatedException;
import com.fruizotero.springjpahibernate.mappers.Mapper;
import com.fruizotero.springjpahibernate.services.NoteService;
import com.fruizotero.springjpahibernate.utils.ApiResponse;
import com.fruizotero.springjpahibernate.utils.ResponseMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NoteController {

    private NoteService noteService;
    private Mapper<NoteEntity, NoteDto> noteMapper;

    public NoteController(NoteService noteService, Mapper<NoteEntity, NoteDto> noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @GetMapping(path = "/notes")
    public ResponseEntity<ApiResponse<List<NoteDto>>> getNotes() {
        return ResponseEntity.ok(
                ApiResponse.<List<NoteDto>>builder()
                        .success(true)
                        .data(noteService.getAllNotes()
                                .stream()
                                .map(noteMapper::mapTo)
                                .toList())
                        .message(ResponseMessages.GET_NOTES.getMessage())
                        .build()
        );

    }

    @GetMapping(path = "/notes/{id}")
    public ResponseEntity<ApiResponse<List<NoteDto>>> getNotesByUser(@PathVariable int id) {

        return ResponseEntity.ok(
                ApiResponse.<List<NoteDto>>builder()
                        .success(true)
                        .data(noteService.getAllNotesUser(id)
                                .stream()
                                .map(noteMapper::mapTo)
                                .toList())
                        .message(ResponseMessages.GET_NOTES_USER.getMessage())
                        .build()
        );

    }

    @PostMapping(path = "/notes")
    public ResponseEntity<ApiResponse<NoteDto>> createNote(@RequestBody NoteDto noteDto) {

        Optional<NoteEntity> noteCreated = noteService.createNote(noteDto);

        if (noteCreated.isEmpty())
            throw new NotCreatedException(ResponseMessages.CREATE_NOTE_ERROR.getMessage());

        return ResponseEntity.ok(
                ApiResponse.<NoteDto>builder()
                        .success(true)
                        .data(noteCreated.map(noteMapper::mapTo).get())
                        .message(ResponseMessages.CREATE_NOTE.getMessage())
                        .build()
        );

    }

    @PutMapping(path = "/notes/{id}")
    public ResponseEntity<ApiResponse<NoteDto>> updateNote(@RequestBody NoteDto noteDto, @PathVariable int id) {

        noteDto.setId(id);

        Optional<NoteEntity> noteUpdated = noteService.updateNote(noteDto);

        if (noteUpdated.isEmpty())
            throw new NotUpdatedException(ResponseMessages.UPDATE_NOTE_ERROR.getMessage());

        return ResponseEntity.ok(
                ApiResponse.<NoteDto>builder()
                        .success(true)
                        .data(noteUpdated.map(noteMapper::mapTo).get())
                        .message(ResponseMessages.UPDATE_NOTE.getMessage())
                        .build()
        );

    }


    @DeleteMapping(path = "/notes/{id}")
    public ResponseEntity<ApiResponse<String>> deleteNote(@PathVariable int id) {

        noteService.deleteNote(id);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message(ResponseMessages.DELETE_NOTE.getMessage())
                        .build());

    }


}
