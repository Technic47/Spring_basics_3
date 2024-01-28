package ru.gb.springdemo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Reader extends BaseEntity{
  private String name;

  private List<Book> books = new ArrayList<>();

  public Reader(String name) {
    super();
    this.name = name;
  }
}
