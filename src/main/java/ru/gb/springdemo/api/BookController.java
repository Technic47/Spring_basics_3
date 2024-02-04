package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.springdemo.dto.BookDto;
import ru.gb.springdemo.model.BaseEntity;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController extends AbstractController<Book, BookService> {
    public BookController(BookService service) {
        super(service);
    }

    @Operation(summary = "Get Book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book is found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDto.class))}),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)})
    @Override
    public ResponseEntity<BookDto> getItem(@PathVariable Long id) {
        return super.getItem(id);
    }

    @Operation(summary = "Delete Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book is deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)})
    @Override
    public ResponseEntity deleteItem(@PathVariable Long id) {
        return super.deleteItem(id);
    }

    @Operation(summary = "Create a new Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book is created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDto.class))}),
            @ApiResponse(responseCode = "400", description = "Wrong Book request Body",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Book already exists",
                    content = @Content)})
    @Override
    @PostMapping
    public ResponseEntity<BookDto> createItem(String bookName) {
        BaseEntity savedBook = service.save(new Book(bookName));
        return ResponseEntity.ok(savedBook.createDto());
    }
}
