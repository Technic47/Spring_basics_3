package ru.gb.springdemo.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
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
    private final ReaderService readerService;

    protected IssuerService(IssueJpaRepository repository, BookService bookService, ReaderService readerService) {
        super(repository);
        this.bookService = bookService;
        this.readerService = readerService;
    }


    public Issue issue(IssueRequest request) {
        Book book = bookService.getById(request.getBookId());
        Reader reader = readerService.getById(request.getReaderId());
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
        readerService.save(reader);
        return issue;
    }

    public Issue close(long id) {
        Issue issue = getById(id);
        Reader reader = readerService.getById(issue.getReaderId());
        Book book = bookService.getById(issue.getBookId());

        issue.setReturned_at(LocalDateTime.now());
        reader.getBooks().remove(book);
        repository.save(issue);
        readerService.save(reader);
        return issue;
    }

    private boolean readerIsAllowed(Reader reader, Book book) {
        List<Book> bookList = reader.getBooks();
        return bookList.size() < bookLimit || !bookList.contains(book);
    }
}
