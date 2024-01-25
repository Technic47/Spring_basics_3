package ru.gb.springdemo.model;

public abstract class BaseEntity {
    protected static long sequence = 1L;

    protected long id;

    public BaseEntity() {
        this.id = sequence++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
