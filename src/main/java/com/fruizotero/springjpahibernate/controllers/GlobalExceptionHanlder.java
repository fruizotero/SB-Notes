package com.fruizotero.springjpahibernate.controllers;

import com.fruizotero.springjpahibernate.exceptions.NotCreatedException;
import com.fruizotero.springjpahibernate.exceptions.NotFoundException;
import com.fruizotero.springjpahibernate.exceptions.NotUpdatedException;
import com.fruizotero.springjpahibernate.exceptions.NotValidDataException;
import com.fruizotero.springjpahibernate.utils.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHanlder {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFoundException(NotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.<String>builder()
                        .message(ex.getMessage())
                        .success(false)
                        .build());

    }

    @ExceptionHandler(NotCreatedException.class)
    public ResponseEntity<ApiResponse<String>> handleNotCreatedException(NotCreatedException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.<String>builder()
                        .message(ex.getMessage())
                        .success(false)
                        .build());
    }


    @ExceptionHandler(NotUpdatedException.class)
    public ResponseEntity<ApiResponse<String>> handleNotUpdatedException(NotUpdatedException ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.<String>builder()
                        .message(ex.getMessage())
                        .success(false)
                        .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleSqlException(SQLException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.<String>builder()
                        .message(ex.getMessage())
                        .success(false)
                        .build());
    }

    @ExceptionHandler(NotValidDataException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleNotValidDataException(NotValidDataException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.<Map<String, String>>builder()
                        .message(ex.getMessage())
                        .data(ex.getErrors())
                        .success(false)
                        .build());
    }
}
