package ru.gb.springdemo.service;

import ru.gb.springdemo.model.BaseEntity;
import ru.gb.springdemo.repository.AbstractRepository;

import java.util.List;

public abstract class AbstractService<T extends BaseEntity, R extends AbstractRepository<T>> {
    protected final R repository;

    protected AbstractService(R repository) {
        this.repository = repository;
    }

    public T getById(long id) {
        return repository.getById(id);
    }

    public boolean deleteById(long id){
        return repository.deleteById(id);
    }

    public T save(T item){
        return repository.save(item);
    }

    public List<T> getAll(){
        return repository.getAll();
    }
}
