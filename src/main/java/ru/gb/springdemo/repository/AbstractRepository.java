package ru.gb.springdemo.repository;

import ru.gb.springdemo.model.BaseEntity;

import java.util.List;

public abstract class AbstractRepository<T extends BaseEntity> {
    public abstract T getById(long id);

    public abstract boolean deleteById(long id);

    public abstract T save(T item);

    public abstract List<T> getAll();
}
