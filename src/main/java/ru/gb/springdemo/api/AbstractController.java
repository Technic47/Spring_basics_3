package ru.gb.springdemo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb.springdemo.dto.BaseDto;
import ru.gb.springdemo.model.BaseEntity;
import ru.gb.springdemo.service.AbstractService;

public abstract class AbstractController<T extends BaseEntity, S extends AbstractService> {
    protected final S service;

    public AbstractController(S service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public <DTO extends BaseDto> ResponseEntity<DTO> getItem(@PathVariable long id) {
        T item = (T) service.getById(id);
        return ResponseEntity.ok(item.createDto());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable long id) {
        boolean result = service.deleteById(id);
        if (result) {
            return ResponseEntity.ok(true);
        } else return ResponseEntity.status(404).body("Item with id - " + id + " not found.");
    }

    public abstract <DTO extends BaseDto> ResponseEntity<DTO> createItem(String itemName);
}
