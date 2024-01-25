package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Reader extends BaseEntity{
  private String name;

  private List<Book> books;

  public Reader(String name) {
    super();
    this.name = name;
  }
}
