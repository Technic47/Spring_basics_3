package ru.gb.springdemo.model;

import lombok.Data;

@Data
public class Book extends BaseEntity {
    private final String name;

    public Book(String name) {
        super();
        this.name = name;
    }
}
