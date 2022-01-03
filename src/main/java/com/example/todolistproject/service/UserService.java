package com.example.todolistproject.service;

import com.example.todolistproject.model.dto.UserDto;
import com.example.todolistproject.model.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    User findByLogin(String login);
    List<UserDto> getAllUsers();
    UserDto findById(Long id);
    void delete(Long id);
}

