package ru.yolshin.book.book.DAO;

import ru.yolshin.book.book.entity.Book;
import ru.yolshin.book.book.service.BookService;

import java.util.List;
import java.util.Map;

public interface BookDAO {
    List<Book> findAll();
    List<Book> findAll(String sub);
    Book create(Book book);
}
