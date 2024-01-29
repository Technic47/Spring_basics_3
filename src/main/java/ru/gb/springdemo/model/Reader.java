package ru.gb.springdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
