package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.dto.BookDto;
import ru.gb.springdemo.dto.ReaderDto;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reader")
public class ReaderController extends AbstractController<Reader, ReaderService> {

    public ReaderController(ReaderService service) {
        super(service);
    }

    @Operation(summary = "Get Reader by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reader is found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReaderDto.class))}),
            @ApiResponse(responseCode = "404", description = "Reader not found",
                    content = @Content)})
    @Override
    public ResponseEntity<ReaderDto> getItem(long id) {
        return super.getItem(id);
    }

    @Operation(summary = "Delete Reader")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reader is deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reader not found",
                    content = @Content)})
    @Override
    public ResponseEntity deleteItem(long id) {
        return super.deleteItem(id);
    }

    @Operation(summary = "Create a new Reader")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reader is created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReaderDto.class))}),
            @ApiResponse(responseCode = "400", description = "Wrong Reader request Body",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Reader already exists",
                    content = @Content)})
    @Override
    @PostMapping
    public ResponseEntity<ReaderDto> createItem(String readerName) {
        Reader savedReader = service.save(new Reader(readerName));
        return ResponseEntity.ok(savedReader.createDto());
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity getIssuedBooks(@PathVariable long id) {
        List<BookDto> issuedBooks = service.getIssuedBooks(id)
                .stream()
                .map(Book::createDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(issuedBooks);
    }
}
