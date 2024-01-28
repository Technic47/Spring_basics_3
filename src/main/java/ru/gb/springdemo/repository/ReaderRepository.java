package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ReaderRepository extends AbstractRepository<Reader> {

    private final List<Reader> readers;

    public ReaderRepository() {
        this.readers = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        readers.addAll(List.of(
                new ru.gb.springdemo.model.Reader("Игорь")
        ));
    }

    public Reader getById(long id) {
        return readers.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteById(long id) {
        Optional<Reader> first = readers.stream()
                .filter(book -> book.getId() == id)
                .findFirst();
        if (first.isPresent()) {
            readers.remove(first.get());
            return true;
        } else return false;
    }

    public Reader save(Reader item) {
        readers.add(item);
        return item;
    }

    @Override
    public List<Reader> getAll() {
        return readers;
    }
}
