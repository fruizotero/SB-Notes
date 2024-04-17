package com.fruizotero.springjpahibernate.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private List<Integer> roles;
//    private List<RoleDto> roles;
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private Set<Integer> rolesIds;
}
