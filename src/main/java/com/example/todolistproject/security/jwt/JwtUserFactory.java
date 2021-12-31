package com.example.todolistproject.security.jwt;

import com.example.todolistproject.model.entity.Role;
import com.example.todolistproject.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getAuthorName(),
                user.getLogin(),
                user.getPassword(),
                user.getCreated(),
                user.getUpdated(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        // it's aloud add only user's role which would match with name in method hasRole()
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
