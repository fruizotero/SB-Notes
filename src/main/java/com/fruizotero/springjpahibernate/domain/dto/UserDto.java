package com.fruizotero.springjpahibernate.domain.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {

    private int id;
    private String email;
    private String password;
    private List<RoleDto> roles;
    private Set<Integer> rolesIds;
}
