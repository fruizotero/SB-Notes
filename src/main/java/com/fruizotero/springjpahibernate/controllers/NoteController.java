package com.fruizotero.springjpahibernate.controllers;

import com.fruizotero.springjpahibernate.domain.dto.NoteDto;
import com.fruizotero.springjpahibernate.domain.entities.NoteEntity;
import com.fruizotero.springjpahibernate.mappers.Mapper;
import com.fruizotero.springjpahibernate.services.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private NoteService noteService;
    private Mapper<NoteEntity, NoteDto> noteMapper;

    public NoteController(NoteService noteService, Mapper<NoteEntity, NoteDto> noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @GetMapping(path = "/notes")
    public ResponseEntity<List<NoteDto>> getNotes() {
        return new ResponseEntity<>(noteService.getAllNotes().stream().map(noteMapper::mapTo).toList(), HttpStatus.OK);
    }

    @GetMapping(path = "/notes/{id}")
    public ResponseEntity<List<NoteDto>> getNotesByUser(@PathVariable int id) {

        return new ResponseEntity<>(noteService.getAllNotesUser(id).stream().map(noteMapper::mapTo).toList(), HttpStatus.OK);

    }

    @PostMapping(path = "/notes")
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto) {

        return noteService.createNote(noteDto).map(note -> new ResponseEntity<>(noteMapper.mapTo(note), HttpStatus.CREATED)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }

    @PutMapping(path = "/notes/{id}")
    public ResponseEntity<NoteDto> updateNote(@RequestBody NoteDto noteDto, @PathVariable int id) {

        noteDto.setId(id);

        return noteService.updateNote(noteDto).map(note -> new ResponseEntity<>(noteMapper.mapTo(note), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }


    @DeleteMapping(path = "/notes/{id}")
    public ResponseEntity deleteNote(@PathVariable int id){

        noteService.deleteNote(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
