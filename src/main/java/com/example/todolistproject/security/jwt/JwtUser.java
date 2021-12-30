package com.example.todolistproject.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

public class JwtUser implements UserDetails {

    private final Long id;
    private final String authorName;
    private final String login;
    private final String password;
    private final LocalDateTime created;
    private final LocalDateTime updated;
    // interface for user's accesses
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id,
                   String authorName,
                   String login,
                   String password,
                   LocalDateTime created,
                   LocalDateTime updated,
                   Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.authorName = authorName;
        this.login = login;
        this.password = password;
        this.created = created;
        this.updated = updated;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return authorName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public LocalDateTime getCreated() {
        return created;
    }

    @JsonIgnore
    public LocalDateTime getUpdated() {
        return updated;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
