package ru.gb.springdemo.api;

import lombok.Data;

/**
 * Запрос на выдачу
 */
@Data
public class IssueRequest {

    public IssueRequest(Long readerId, Long bookId) {
        this.readerId = readerId;
        this.bookId = bookId;
    }

    /**
     * Идентификатор читателя
     */
    private Long readerId;

    /**
     * Идентификатор книги
     */
    private Long bookId;

}
