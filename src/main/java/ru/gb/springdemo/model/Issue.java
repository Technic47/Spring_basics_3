package ru.gb.springdemo.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
// @Entity
public class Issue extends BaseEntity{
  private final long bookId;
  private final long readerId;

  /**
   * Дата выдачи
   */
  private final LocalDateTime issued_at;
  private LocalDateTime returned_at;

  public Issue(long bookId, long readerId) {
    super();
    this.id = sequence++;
    this.bookId = bookId;
    this.readerId = readerId;
    this.issued_at = LocalDateTime.now();
  }
}
