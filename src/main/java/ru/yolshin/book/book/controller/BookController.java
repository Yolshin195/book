package ru.yolshin.book.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yolshin.book.book.entity.Book;
import ru.yolshin.book.book.service.BookService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/byAuthors")
    public Map<String, List<Book>> findAllByAuthors() {
        return bookService.findAllByAuthors();
    }

    @GetMapping("/symbol/{symbol}")
    public Map<String, Integer> symbol(@PathVariable Character symbol) {

    }

    @PostMapping
    Book create(@RequestBody Book request) {
        return bookService.create(request);
    }
}
