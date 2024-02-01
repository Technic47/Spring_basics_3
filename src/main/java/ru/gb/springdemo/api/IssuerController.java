package ru.gb.springdemo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.dto.BaseDto;
import ru.gb.springdemo.dto.BookDto;
import ru.gb.springdemo.dto.IssueDto;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueRequest;
import ru.gb.springdemo.service.IssuerService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/issue")
public class IssuerController extends AbstractController<Issue, IssuerService>{

    public IssuerController(IssuerService service) {
        super(service);
    }

    @Operation(summary = "Get Book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book is found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class))}),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)})
    @Override
    public  ResponseEntity<IssueDto> getItem(long id) {
        return super.getItem(id);
    }

    @Operation(summary = "Delete Issue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Issue is deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Issue not found",
                    content = @Content)})
    @Override
    public ResponseEntity deleteItem(long id) {
        return super.deleteItem(id);
    }

    @Operation(summary = "Issue Book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Issue created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class))}),
            @ApiResponse(responseCode = "400", description = "Wrong request Body",
                    content = @Content)})
    @PostMapping()
    public ResponseEntity<IssueDto> issueBook(@RequestBody IssueRequest request) {
        log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e){
            return ResponseEntity.status(409).build();
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(issue.createDto());
    }

    @Operation(summary = "Close Issue")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Issue closed",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = IssueDto.class))}),
            @ApiResponse(responseCode = "404", description = "Issue not found",
                    content = @Content)})
    @PutMapping("/{issueId}")
    public ResponseEntity<IssueDto> closeIssue(@PathVariable long issueId){
        Issue closed = service.close(issueId);
        return ResponseEntity.ok(closed.createDto());
    }
    @Operation(summary = "Not implemented")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "null")})
    @Override
    public ResponseEntity<Issue> createItem(String IssueName) {
        return null;
    }
}
