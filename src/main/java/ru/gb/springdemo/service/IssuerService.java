package ru.gb.springdemo.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService extends AbstractService<Issue, IssueRepository> {
    @Value("${application.max-allowed-books}")
    private int bookLimit = 1;

    // спринг это все заинжектит
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public Issue issue(IssueRequest request) {
        Book book = bookRepository.getById(request.getBookId());
        Reader reader = readerRepository.getById(request.getReaderId());
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
        return issue;
    }

    public Issue close(long id) {
        Issue issue = getById(id);
        Reader reader = readerRepository.getById(issue.getReaderId());
        Book book = bookRepository.getById(issue.getBookId());

        issue.setReturned_at(LocalDateTime.now());
        reader.getBooks().remove(book);
        repository.save(issue);
        readerRepository.save(reader);
        return issue;
    }

    private boolean readerIsAllowed(Reader reader, Book book) {
        List<Book> bookList = reader.getBooks();
        return bookList.size() < bookLimit || !bookList.contains(book);
    }

    public Issue getById(long id) {
        return repository.getById(id);
    }
}
