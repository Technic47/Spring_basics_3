package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Reader;

@Repository
public interface ReaderJpaRepository extends AbstractJpaRepository<Reader> {
}
