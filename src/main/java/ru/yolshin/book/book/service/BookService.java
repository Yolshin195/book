package ru.yolshin.book.book.service;

import ru.yolshin.book.book.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Book> findAll();
    Map<String, List<Book>> groupByAuthor();
    Map<String, Integer> findAuthor(String sub);
    Book create(Book book);
}
