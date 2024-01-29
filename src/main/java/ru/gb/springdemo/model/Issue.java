package ru.gb.springdemo.model;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Запись о факте выдачи книги (в БД)
 */

@Entity
public class Issue extends BaseEntity {
    private long bookId;
    private long readerId;

    /**
     * Дата выдачи
     */
    private LocalDateTime issued_at;
    private LocalDateTime returned_at;

    public Issue(long bookId, long readerId) {
        super();
        this.bookId = bookId;
        this.readerId = readerId;
        this.issued_at = LocalDateTime.now();
    }

    public Issue() {
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public LocalDateTime getIssued_at() {
        return issued_at;
    }

    public void setIssued_at(LocalDateTime issued_at) {
        this.issued_at = issued_at;
    }

    public LocalDateTime getReturned_at() {
        return returned_at;
    }

    public void setReturned_at(LocalDateTime returned_at) {
        this.returned_at = returned_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Issue)) return false;
        if (!super.equals(o)) return false;
        Issue issue = (Issue) o;
        return bookId == issue.bookId && readerId == issue.readerId && Objects.equals(issued_at, issue.issued_at) && Objects.equals(returned_at, issue.returned_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bookId, readerId, issued_at, returned_at);
    }
}
