package ru.gb.springdemo.service;

import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookJpaRepository;

@Service
public class BookService extends AbstractService<Book, BookJpaRepository> {
    public BookService(BookJpaRepository bookRepository) {
        super(bookRepository);
    }
}
