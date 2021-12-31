package com.example.todolistproject.model.dto;

import com.example.todolistproject.model.entity.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    Long id;
    @NotBlank
    String authorName;
    @NotBlank
    String login;
    @NotBlank
    String password;
    @CreatedDate
    LocalDateTime created;
    @LastModifiedDate
    LocalDateTime updated;
    List<Role> roles;
}
