package ru.gb.springdemo.dto;

import ru.gb.springdemo.model.Book;

public class BookDto extends BaseDto{
    private String name;
    private String readerName;

    public BookDto create(Book book){
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setName(book.getName());
        String newReaderName = book.getReader() == null ? "" : book.getReader().getName();
        dto.setReaderName(newReaderName);
        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }
}
