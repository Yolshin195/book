package ru.yolshin.book.book.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ru.yolshin.book.book.entity.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDAOImpl implements BookDAO {
    private static final String CREATE_BOOK_SQL = "insert into BOOK (id, title, author, description) values (?, ?, ?, ?)";
    private static final String FIND_ALL_BOOK_SQL = "select * from book";
    private static final String FIND_ALL_BOOK_CONTAIN_SUB_SQL = "select * from book where title like ?";
    JdbcTemplate jdbcTemplate;
    RowMapper<Book> bookRowMapper;

    BookDAOImpl(JdbcTemplate jdbcTemplate, RowMapper<Book> bookRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookRowMapper = bookRowMapper;
    }

    @Override
    public Book create(Book book) {
        Integer status = jdbcTemplate.update(CREATE_BOOK_SQL,
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription()
        );

        return book;
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(FIND_ALL_BOOK_SQL, bookRowMapper);
    }

    @Override
    public List<Book> findAll(String sub) {
        return jdbcTemplate.query(FIND_ALL_BOOK_CONTAIN_SUB_SQL, bookRowMapper, "%" + sub + "%");
    }

}
