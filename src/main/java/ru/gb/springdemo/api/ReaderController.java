package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.dto.BaseDto;
import ru.gb.springdemo.dto.BookDto;
import ru.gb.springdemo.dto.UserDto;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.UserEntity;
import ru.gb.springdemo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reader")
public class ReaderController extends AbstractController<UserEntity, UserService> {

    public ReaderController(UserService service) {
        super(service);
    }

    @Operation(summary = "Get Reader by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reader is found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "404", description = "Reader not found",
                    content = @Content)})
    @Override
    public ResponseEntity<UserDto> getItem(@PathVariable Long id) {
        return super.getItem(id);
    }

    @Operation(summary = "Delete Reader")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reader is deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Reader not found",
                    content = @Content)})
    @Secured({"ROLE_ADMIN"})
    @Override
    public ResponseEntity deleteItem(@PathVariable Long id) {
        return super.deleteItem(id);
    }

    @Operation(summary = "Create a new Reader")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reader is created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "400", description = "Wrong Reader request Body",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Reader already exists",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<UserDto> createItem(String readerName, String readerPass) {
        UserEntity savedReader = service.createReader(readerName, readerPass);
        return ResponseEntity.ok(savedReader.createDto());
    }

    @Override
    public <DTO extends BaseDto> ResponseEntity<DTO> createItem(String itemName) {
        return ResponseEntity.status(404).build();
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity getIssuedBooks(@PathVariable Long id) {
        List<BookDto> issuedBooks = service.getIssuedBooks(id)
                .stream()
                .map(Book::createDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(issuedBooks);
    }
}
