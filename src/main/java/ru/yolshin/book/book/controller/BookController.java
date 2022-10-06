package ru.yolshin.book.book.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yolshin.book.book.entity.Book;
import ru.yolshin.book.book.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    Book create(@RequestBody Book request) {
        return bookService.create(request);
    }
}
