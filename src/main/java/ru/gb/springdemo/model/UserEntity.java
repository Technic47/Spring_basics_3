package ru.gb.springdemo.model;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import ru.gb.springdemo.UserRole;
import ru.gb.springdemo.dto.UserDto;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class UserEntity extends BaseEntity implements UserDetails {
    private String username;
    private String password;
    private String email;
    private LocalDateTime created;
    private LocalDateTime updated;
    private boolean active;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> authorities = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    @Override
    public Set<UserRole> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public UserDto createDto() {
        UserDto dto = new UserDto();
        dto.setId(this.id);
        dto.setUserName(this.username);
        dto.setCreated(this.created);
        dto.setUpdated(this.updated);
        dto.setActive(this.active);
        dto.setAuthorities(this.authorities.stream()
                .map(UserRole::getAuthority)
                .collect(Collectors.toSet()));
        dto.setBookNames(this.books.stream()
                .map(Book::getName)
                .collect(Collectors.toSet()));
        return dto;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setAuthorities(Set<UserRole> authorities) {
        this.authorities = authorities;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        if (!super.equals(o)) return false;
        UserEntity that = (UserEntity) o;
        return active == that.active && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(created, that.created) && Objects.equals(updated, that.updated) && Objects.equals(authorities, that.authorities) && Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, email, created, updated, active, authorities, books);
    }
}
