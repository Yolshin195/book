package ru.yolshin.book.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yolshin.book.entity.Book;
import ru.yolshin.book.service.BookService;

import java.util.List;
import java.util.Map;


@Tag(name = "Book REST API operations")
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    @Operation(summary = "return all books")
    List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/groupBy/author")
    @Operation(summary = "return all books grouped by author")
    public Map<String, List<Book>> groupByAuthor() {
        return bookService.groupByAuthor();
    }

    @GetMapping("/symbol/{symbol}")
    @Operation(summary = "returning a list of 10 authors,\n" +
            "in the title of books in which this symbol occurs most often")
    public Map<String, Integer> symbol(@PathVariable String symbol) {
        return bookService.findAuthor(symbol);
    }

    @PostMapping
    @Operation(summary = "create new Book")
    ResponseEntity<Book> create(@RequestBody Book request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookService.create(request));
    }
}
