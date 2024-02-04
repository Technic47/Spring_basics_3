package ru.gb.springdemo.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.*;
import ru.gb.springdemo.repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class IssuerService extends AbstractService<Issue, IssueJpaRepository> {
    @Value("${application.max-allowed-books}")
    private int bookLimit = 1;

    private final BookService bookService;
    private final UserService userService;

    protected IssuerService(IssueJpaRepository repository,
                            BookService bookService,
                            UserService userService) {
        super(repository);
        this.bookService = bookService;
        this.userService = userService;
    }


    public Issue issue(IssueRequest request) {
        Book book = bookService.getById(request.getBookId());
        UserEntity reader = userService.getById(request.getReaderId());
        if (book == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (reader == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
        if (!readerIsAllowed(reader, book)) {
            throw new RuntimeException("Пользователь не может принимать книги.");
        }
        Issue issue = new Issue(request.getBookId(), request.getReaderId());
        repository.save(issue);
        reader.getBooks().add(book);
        userService.save(reader);
        return issue;
    }

    public Issue close(long id) {
        Issue issue = getById(id);
        UserEntity reader = userService.getById(issue.getReaderId());
        Book book = bookService.getById(issue.getBookId());

        issue.setReturned_at(LocalDateTime.now());
        reader.getBooks().remove(book);
        repository.save(issue);
        userService.save(reader);
        return issue;
    }

    private boolean readerIsAllowed(UserEntity reader, Book book) {
        List<Book> bookList = reader.getBooks();
        return bookList.size() < bookLimit || !bookList.contains(book);
    }
}
