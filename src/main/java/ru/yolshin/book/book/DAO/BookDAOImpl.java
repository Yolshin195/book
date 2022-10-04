package ru.yolshin.book.book.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yolshin.book.book.entity.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOImpl implements BookDAO {
    JdbcTemplate jdbcTemplate;
    RowMapper<Book> bookRowMapper;

    BookDAOImpl(JdbcTemplate jdbcTemplate, RowMapper<Book> bookRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookRowMapper = bookRowMapper;
    }

    @Override
    public List<Book> findAll() {
        List<Book> bookList = jdbcTemplate.query("select * from book", bookRowMapper);
        return bookList;
    }

    @Override
    public Map<String, List<Book>> findAllByAuthors() {
        Map<String, List<Book>> bookMap = new HashMap<>();
        findAll().forEach(book -> {
            if (bookMap.containsKey(book.getAuthor())) {
                bookMap.get(book.getAuthor()).add(book);
            } else {
                List<Book> bookList = new ArrayList<>();
                bookList.add(book);
                bookMap.put(book.getAuthor(), bookList);
            }
        });

        return bookMap;
    }

    public Map<String, Integer> findAuthor() {
        Map<String, Integer> bookMap = new HashMap<>();

        return bookMap;
    }

    @Override
    public Book create(Book book) {
        Integer status = jdbcTemplate.update("insert into BOOK (id, title, author, description) values (?, ?, ?, ?)",
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription()
        );

        return book;
    }
}
