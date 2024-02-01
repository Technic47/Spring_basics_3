package ru.gb.springdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import ru.gb.springdemo.dto.BaseDto;
import ru.gb.springdemo.dto.BookDto;

import java.util.Objects;


@Entity
public class Book extends BaseEntity {
    private String name;
    @ManyToOne
    private Reader reader;

    public Book(String name) {
        super();
        this.name = name;
    }

    public Book() {
    }

    @Override
    public BookDto createDto(){
        BookDto dto = new BookDto();
        dto.setId(this.id);
        dto.setName(this.name);
        String newReaderName = this.reader == null ? "" : this.reader.getName();
        dto.setReaderName(newReaderName);
        return dto;
    }

    public String getName() {
        return name;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
