package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.UserEntity;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends AbstractJpaRepository<UserEntity> {
    Optional<UserEntity> findByUsername(String userName);
}
