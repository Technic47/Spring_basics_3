package ru.gb.springdemo;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_READER;

    @Override
    public String getAuthority() {
        return name();
    }
}
