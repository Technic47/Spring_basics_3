package ru.gb.springdemo.dto;

import java.util.List;

public class ReaderDto extends BaseDto{
    private String name;
    private List<String> bookNames;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBookNames() {
        return bookNames;
    }

    public void setBookNames(List<String> bookNames) {
        this.bookNames = bookNames;
    }
}
