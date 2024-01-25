package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BookRepository extends AbstractRepository<Book> {

    private final List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        books.addAll(List.of(
                new Book("война и мир"),
                new Book("метрвые души"),
                new Book("чистый код")
        ));
    }

    public Book getById(long id) {
        return books.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteById(long id) {
        Optional<Book> first = books.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
        if (first.isPresent()) {
            books.remove(first.get());
            return true;
        } else return false;
    }

    public Book save(Book item) {
        books.add(item);
        return item;
    }
}
