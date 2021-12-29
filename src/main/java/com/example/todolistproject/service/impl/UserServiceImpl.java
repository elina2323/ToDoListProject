package com.example.todolistproject.service.impl;

import com.example.todolistproject.dao.RoleRepo;
import com.example.todolistproject.dao.UserRepo;
import com.example.todolistproject.mapper.UserMapper;
import com.example.todolistproject.model.dto.UserDto;
import com.example.todolistproject.model.entity.Role;
import com.example.todolistproject.model.entity.User;
import com.example.todolistproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto saveUser(UserDto userDto) {

        User user = UserMapper.INSTANCE.mapToUser(userDto);
        Role roleUser = roleRepo.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setRoles(userRoles);
        userRepo.save(user);

        log.info("IN UserServiceImpl saveUser - user {} successfully saved", userDto);

        return UserMapper.INSTANCE.mapToUserDto(user);
    }

    @Override
    public UserDto findByAuthorName(String authorName) {

        User user = userRepo.findByAuthorName(authorName);

        log.info("IN UserServiceImpl findByAuthorName - user: {} found by authorName: {}", user, authorName);

        return UserMapper.INSTANCE.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> userList = userRepo.findAll();

        log.info("IN UserServiceImpl getAllUsers - {} users found", userList.size());

        return UserMapper.INSTANCE.mapToUserDtoList(userList);
    }

    @Override
    public UserDto findById(Long id) {

        User user = userRepo.findById(id).orElse(null);

        if (user == null) {
            log.warn("IN UserServiceImpl findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN UserServiceImpl findById - user: {} found by id: {}", user, id);

        return UserMapper.INSTANCE.mapToUserDto(user);
    }

    @Override
    public void delete(Long id) {

        userRepo.deleteById(id);

        log.info("IN UserServiceImpl delete - user with id: {} successfully deleted", id);

    }
}
