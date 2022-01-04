package com.example.todolistproject.service;

import com.example.todolistproject.model.dto.UserDto;
import com.example.todolistproject.model.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    User findByUsername(String username);
    List<User> getAllUsers();
    User findById(Long id);
    void delete(Long id);
}

