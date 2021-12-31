package com.example.todolistproject.security;

import com.example.todolistproject.dao.UserRepo;
import com.example.todolistproject.model.dto.UserDto;
import com.example.todolistproject.model.entity.User;
import com.example.todolistproject.security.jwt.JwtUser;
import com.example.todolistproject.security.jwt.JwtUserFactory;
import com.example.todolistproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// SS understands objects from its environment, UserDetailsService is an auxiliary class
public class JwtUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(JwtUserDetailsService.class);

    private final UserRepo userRepo;

    public JwtUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    // takes user from DB by login, converts and return jwtUser
    public UserDetails loadUserByUsername(String authorName) throws UsernameNotFoundException {
        User user = userRepo.findByAuthorName(authorName);

        if (user == null) {
            throw new UsernameNotFoundException("User with authorName: " + authorName + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);

        log.info("IN JwtUserDetailsServiceImpl loadUserByUsername - user with authorName: {} successfully loaded", authorName);

        return jwtUser;
    }
}

