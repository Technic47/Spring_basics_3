package ru.gb.springdemo.api;

import lombok.Data;

/**
 * Запрос на выдачу
 */
@Data
public class IssueRequest {

    public IssueRequest(long readerId, long bookId) {
        this.readerId = readerId;
        this.bookId = bookId;
    }

    /**
     * Идентификатор читателя
     */
    private long readerId;

    /**
     * Идентификатор книги
     */
    private long bookId;

}
