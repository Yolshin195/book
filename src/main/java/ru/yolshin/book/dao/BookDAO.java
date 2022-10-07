package ru.yolshin.book.dao;

import ru.yolshin.book.entity.Book;

import java.util.List;


public interface BookDAO {
    Book findById(Long id);
    List<Book> findAll();
    List<Book> findAll(String sub);
    Book create(Book book);
    Long nextId();
}
