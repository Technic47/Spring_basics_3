package ru.gb.springdemo.demo;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@Component
public class ApplicationEventListener {
    private final BookService bookService;
    private final ReaderService readerService;
    private final IssuerService issuerService;

    public ApplicationEventListener(BookService bookService, ReaderService readerService, IssuerService issuerService) {
        this.bookService = bookService;
        this.readerService = readerService;
        this.issuerService = issuerService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStart() {
        readerService.save(new Reader("Игорь"));
        bookService.save(new Book("война и мир"));
        bookService.save(new Book("метрвые души"));
        bookService.save(new Book("чистый код"));
        List<Book> books = bookService.getAll();
        List<Reader> readers = readerService.getAll();
        if (!books.isEmpty() && !readers.isEmpty()) {
            issuerService.issue(new IssueRequest(readers.get(0).getId(), books.get(0).getId()));
        }
    }
}
