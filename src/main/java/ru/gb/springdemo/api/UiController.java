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
import ru.gb.springdemo.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/ui/")
public class UiController {
    private final BookService bookService;
    private final IssuerService issuerService;

    private final UserService userService;

    public UiController(BookService bookService,
                        IssuerService issuerService,
                        UserService userService) {
        this.bookService = bookService;
        this.issuerService = issuerService;
        this.userService = userService;
    }

    @GetMapping("/books")
    public String getBooks(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "allBooks";
    }

    @GetMapping("/readers")
    public String getReaders(Model model) {
        model.addAttribute("readers", userService.getAll());
        return "allReaders";
    }

    @GetMapping("/reader/{id}")
    public String getReaderBooks(Model model, @PathVariable long id) {
        model.addAttribute("reader", userService.getById(id));
        return "readerInfo";
    }

    @GetMapping("/issues")
    public String getIssues(Model model) {
        List<TableIssueData> data = new ArrayList<>();
        for (Issue issue : issuerService.getAll()) {
            String bookName = bookService.getById(issue.getBookId()).getName();
            String readerName = userService.getById(issue.getReaderId()).getUsername();
            data.add(new TableIssueData(bookName, readerName, issue.getIssued_at(), issue.getReturned_at()));
        }
        model.addAttribute("issues", data);
        return "allIssues";
    }
}
