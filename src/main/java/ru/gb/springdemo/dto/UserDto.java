package ru.gb.springdemo.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class UserDto extends BaseDto {
    protected long id;
    private String userName;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean active;
    private Set<String> authorities;
    private Set<String> bookNames;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Set<String> getBookNames() {
        return bookNames;
    }

    public void setBookNames(Set<String> bookNames) {
        this.bookNames = bookNames;
    }
}
