package com.example.todolistproject.service.impl;

import com.example.todolistproject.dao.RoleRepo;
import com.example.todolistproject.dao.UserRepo;
import com.example.todolistproject.mapper.UserHistoryMapper;
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

    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {

        //User user = UserHistoryMapper.INSTANCE.toUser(userDto);
        Role roleUser = roleRepo.findByName("USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        User registeredUser = userRepo.save(user);

        log.info("IN UserServiceImpl saveUser - user {} successfully saved", user);

        return registeredUser;

        //return UserHistoryMapper.INSTANCE.toUserDto(user);
    }

    @Override
    public User findByUsername(String username) {

        User user = userRepo.findByUsername(username);

        log.info("IN UserServiceImpl findByLogin - user: {} found by login: {}", user, username);

        return user;

        //return UserHistoryMapper.INSTANCE.toUserDto(user);
    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = userRepo.findAll();

        log.info("IN UserServiceImpl getAllUsers - {} users found", userList.size());

        return userList;
    }

    @Override
    public User findById(Long id) {

        User user = userRepo.findById(id).orElse(null);

        if (user == null) {
            log.warn("IN UserServiceImpl findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN UserServiceImpl findById - user: {} found by id: {}", user, id);

        return user;
    }

    @Override
    public void delete(Long id) {

        userRepo.deleteById(id);

        log.info("IN UserServiceImpl delete - user with id: {} successfully deleted", id);

    }
}
