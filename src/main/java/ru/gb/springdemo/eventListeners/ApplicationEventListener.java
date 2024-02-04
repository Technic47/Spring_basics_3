package ru.gb.springdemo.eventListeners;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.IssueRequest;
import ru.gb.springdemo.model.UserEntity;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.UserService;

import java.util.List;

@Component
public class ApplicationEventListener {
    private final BookService bookService;
    private final IssuerService issuerService;
    private final UserService userService;

    public ApplicationEventListener(BookService bookService,
                                    IssuerService issuerService,
                                    UserService userService) {
        this.bookService = bookService;
        this.issuerService = issuerService;
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStart() {
        userService.createAdmin("admin", "admin");
        userService.createReader("Igor", "igor");

        bookService.save(new Book("война и мир"));
        bookService.save(new Book("метрвые души"));
        bookService.save(new Book("чистый код"));
        List<Book> books = bookService.getAll();
        List<UserEntity> readers = userService.getAll();
        if (!books.isEmpty() && !readers.isEmpty()) {
            issuerService.issue(new IssueRequest(readers.get(0).getId(), books.get(0).getId()));
        }


    }
}
