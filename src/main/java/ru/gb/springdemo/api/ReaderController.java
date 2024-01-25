package ru.gb.springdemo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController extends AbstractController<Reader, ReaderService> {

    public ReaderController(ReaderService service) {
        super(service);
    }

    @Override
    @PostMapping
    public ResponseEntity<Reader> createItem(String readerName) {
        Reader savedReader = service.save(new Reader(readerName));
        return ResponseEntity.ok(savedReader);
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity getIssuedBooks(@PathVariable long id) {
        List<Book> issuedBooks = service.getIssuedBooks(id);
        return ResponseEntity.ok(issuedBooks);
    }
}
