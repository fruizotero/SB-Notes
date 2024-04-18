package com.fruizotero.springjpahibernate.controllers;

import com.fruizotero.springjpahibernate.exceptions.NotFoundException;
import com.fruizotero.springjpahibernate.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class GlobalExceptionHanlder {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNotFoundException(NotFoundException ex) {

        return ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.<String>builder()
                        .message(ex.getMessage())
                        .success(false)

                        .build());

    }
}
