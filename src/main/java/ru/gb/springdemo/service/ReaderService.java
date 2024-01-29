package ru.gb.springdemo.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.ReaderJpaRepository;

import java.util.List;

@Service
public class ReaderService extends AbstractService<Reader, ReaderJpaRepository> {
    public ReaderService(ReaderJpaRepository readerRepository) {
        super(readerRepository);
    }

    public List<Book> getIssuedBooks(long id) {
        return getById(id).getBooks();
    }
}
