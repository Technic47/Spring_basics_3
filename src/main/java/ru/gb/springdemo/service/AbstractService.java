package ru.gb.springdemo.service;

import ru.gb.springdemo.model.BaseEntity;
import ru.gb.springdemo.repository.AbstractRepository;

public abstract class AbstractService<T extends BaseEntity, R extends AbstractRepository<T>> {
    protected R repository;

    public T getById(long id) {
        return repository.getById(id);
    }

    public boolean deleteById(long id){
        return repository.deleteById(id);
    }

    public T save(T item){
        return repository.save(item);
    }
}
