package com.fruizotero.springjpahibernate.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteDto {

    private int id;
    private String title;
    private String content;
    private Integer userId;
}
