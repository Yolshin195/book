package ru.yolshin.book.book.service;

import org.springframework.stereotype.Service;
import ru.yolshin.book.book.DAO.BookDAO;
import ru.yolshin.book.book.entity.Book;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    BookDAO bookDAO;

    BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public Book create(Book book) {
        return bookDAO.create(book);
    }

    @Override
    public Map<String, List<Book>> findAllByAuthors() {
        return bookDAO.findAllByAuthors();
    }
}
