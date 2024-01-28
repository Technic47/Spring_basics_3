package ru.gb.springdemo.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springdemo.dto.TableIssueData;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.service.BookService;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ui/")
public class UiController {
    private final BookService bookService;
    private final ReaderService readerService;
    private final IssuerService issuerService;

    public UiController(BookService bookService, ReaderService readerService, IssuerService issuerService) {
        this.bookService = bookService;
        this.readerService = readerService;
        this.issuerService = issuerService;
    }

    @GetMapping("/books")
    public String getBooks(Model model){
        model.addAttribute("books", bookService.getAll());
        return "allBooks";
    }

    @GetMapping("/readers")
    public String getReaders(Model model){
        model.addAttribute("readers", readerService.getAll());
        return "allReaders";
    }

    @GetMapping("/reader/{id}")
    public String getReaderBooks(Model model, @PathVariable long id){
        model.addAttribute("reader", readerService.getById(id));
        return "readerInfo";
    }

    @GetMapping("/issues")
    public String getIssues(Model model){
        List<TableIssueData> data = new ArrayList<>();
        for (Issue issue : issuerService.getAll()) {
            String bookName = bookService.getById(issue.getBookId()).getName();
            String readerName = readerService.getById(issue.getReaderId()).getName();
            data.add(new TableIssueData(bookName, readerName, issue.getIssued_at(), issue.getReturned_at()));
        }
        model.addAttribute("issues", data);
        return "allIssues";
    }
}
