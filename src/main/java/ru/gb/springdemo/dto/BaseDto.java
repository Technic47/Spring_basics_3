package ru.gb.springdemo.dto;

public abstract class BaseDto {
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
