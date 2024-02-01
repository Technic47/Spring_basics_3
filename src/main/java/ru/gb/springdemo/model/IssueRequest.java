package ru.gb.springdemo.model;

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
