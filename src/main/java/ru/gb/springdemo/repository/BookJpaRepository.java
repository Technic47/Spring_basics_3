package ru.gb.springdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

@Repository
public interface BookJpaRepository extends AbstractJpaRepository<Book>{
}
