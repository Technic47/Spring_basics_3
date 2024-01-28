package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;

@Service
public class ReaderService extends AbstractService<Reader, ReaderRepository> {
    public ReaderService(ReaderRepository readerRepository) {
        super(readerRepository);
    }

    public List<Book> getIssuedBooks(long id) {
        return getById(id).getBooks();
    }
}
