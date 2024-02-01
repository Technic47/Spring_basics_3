package ru.gb.springdemo.dto;

import java.time.LocalDateTime;

public class IssueDto extends BaseDto{
    private long bookId;
    private long readerId;
    private LocalDateTime issued_at;
    private LocalDateTime returned_at;

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
}
