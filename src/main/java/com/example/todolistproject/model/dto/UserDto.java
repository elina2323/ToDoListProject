package com.example.todolistproject.model.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    @NotBlank
    String authorName;
    @NotBlank
    String login;
//    @NotBlank
//    String password;
//    @CreatedDate
//    LocalDateTime created;
//    @LastModifiedDate
//    LocalDateTime updated;
//    List<Role> roles;
}
