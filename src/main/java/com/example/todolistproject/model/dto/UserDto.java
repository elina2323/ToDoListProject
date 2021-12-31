package com.example.todolistproject.model.dto;

import com.example.todolistproject.model.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
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
