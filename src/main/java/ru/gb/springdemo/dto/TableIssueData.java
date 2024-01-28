package ru.gb.springdemo.dto;

import java.time.LocalDateTime;

public class TableIssueData {
    private String bookName;
    private String ReaderName;
    private LocalDateTime issued_at;
    private LocalDateTime returned_at;

    public TableIssueData(String bookName, String readerName, LocalDateTime issued_at, LocalDateTime returned_at) {
        this.bookName = bookName;
        ReaderName = readerName;
        this.issued_at = issued_at;
        this.returned_at = returned_at;
    }

    public TableIssueData() {
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getReaderName() {
        return ReaderName;
    }

    public void setReaderName(String readerName) {
        ReaderName = readerName;
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
