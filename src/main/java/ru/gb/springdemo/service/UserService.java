package ru.gb.springdemo.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.UserRole;
import ru.gb.springdemo.dto.RegistrationRequestDto;
import ru.gb.springdemo.dto.UserDto;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.UserEntity;
import ru.gb.springdemo.repository.UserJpaRepository;

import java.time.LocalDateTime;
import java.util.*;

import static ru.gb.springdemo.UserRole.*;

@Service
public class UserService extends AbstractService<UserEntity, UserJpaRepository> {
    private final PasswordEncoder passwordEncoder;

    protected UserService(UserJpaRepository repository, PasswordEncoder passwordEncoder) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity findByUsername(String userName) {
        Optional<UserEntity> user = repository.findByUsername(userName);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + userName);
        } else {
            return user.get();
        }
    }

    public UserDto registerNewUserAccount(RegistrationRequestDto dto) {
        return constructAndSave(dto.getUsername(),
                dto.getPassword(),
                new HashSet<>(Collections.singletonList(ROLE_READER)))
                .createDto();
    }

    public void createAdmin(String name, String pass) {
        constructAndSave(name, pass, new HashSet<>(Arrays.asList(ROLE_ADMIN, ROLE_READER, ROLE_USER)));
    }

    public UserEntity createReader(String name, String pass) {
        return constructAndSave(name, pass, new HashSet<>(Collections.singletonList(ROLE_READER)));
    }

    private UserEntity constructAndSave(String name, String pass, Set<UserRole> roles) {
        UserEntity newModel = new UserEntity();
        newModel.setUsername(name);
        newModel.setPassword(passwordEncoder.encode(pass));
        newModel.setAuthorities(roles);
        newModel.setCreated(LocalDateTime.now());
        repository.save(newModel);
        return newModel;
    }

    public List<Book> getIssuedBooks(long id) {
        return getById(id).getBooks();
    }
}
