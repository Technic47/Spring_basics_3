package ru.gb.springdemo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb.springdemo.model.BaseEntity;
import ru.gb.springdemo.service.AbstractService;

public abstract class AbstractController<T extends BaseEntity, S extends AbstractService> {
    protected final S service;

    public AbstractController(S service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<T> getItem(@PathVariable long id) {
        T item = (T) service.getById(id);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable long id) {
        boolean result = service.deleteById(id);
        if (result) {
            return ResponseEntity.ok(true);
        } else return ResponseEntity.status(404).body("Item with id - " + id + " not found.");
    }

    public abstract ResponseEntity<T> createItem(String itemName);
}
