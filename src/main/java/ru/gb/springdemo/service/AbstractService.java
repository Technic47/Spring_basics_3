package ru.gb.springdemo.service;

import ru.gb.springdemo.model.BaseEntity;
import ru.gb.springdemo.repository.AbstractJpaRepository;

import java.util.List;
import java.util.Optional;


public abstract class AbstractService<T extends BaseEntity, R extends AbstractJpaRepository<T>> {
    protected final R repository;

    protected AbstractService(R repository) {
        this.repository = repository;
    }

    public T getById(long id) {
        Optional<T> byId = repository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else throw new RuntimeException("Not found with id - " + id);

    }

    public boolean deleteById(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public T save(T item) {
        return repository.save(item);
    }

    public List<T> getAll() {
        return (List<T>) repository.findAll();
    }
}
