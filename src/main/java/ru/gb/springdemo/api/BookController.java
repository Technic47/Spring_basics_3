package ru.gb.springdemo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController extends AbstractController<Book, BookService> {


    public BookController(BookService service) {
        super(service);
    }

    @Override
    @PostMapping
    public ResponseEntity<Book> createItem(String bookName) {
        Book savedBook = service.save(new Book(bookName));
        return ResponseEntity.ok(savedBook);
    }
}
