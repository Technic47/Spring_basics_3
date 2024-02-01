package ru.gb.springdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import ru.gb.springdemo.dto.ReaderDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Entity
public class Reader extends BaseEntity {
    private String name;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Book> books;

    public Reader(String name) {
        super();
        this.name = name;
    }

    public Reader() {
    }

    @Override
    public ReaderDto createDto() {
        ReaderDto dto = new ReaderDto();
        dto.setId(this.id);
        dto.setName(this.name);
        List<String> bookNames = new ArrayList<>();
        if (!books.isEmpty()) {
            bookNames = books.stream()
                    .map(Book::getName)
                    .collect(Collectors.toList());
        }
        dto.setBookNames(bookNames);
        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reader)) return false;
        if (!super.equals(o)) return false;
        Reader reader = (Reader) o;
        return Objects.equals(name, reader.name) && Objects.equals(books, reader.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, books);
    }
}
