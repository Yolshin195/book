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
    private static final String FIND_BY_ID_SQL = "select * from book where id = ?";
    private static final String FIND_ALL_BOOK_SQL = "select * from book order by title DESC";
    private static final String FIND_ALL_BOOK_CONTAIN_SUB_SQL = "select * from book where title like ?";
    private static final String DELETE_BOOK_SQL = "delete from book where id = ?";

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
    public Book findById(Long id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID_SQL, bookRowMapper, id);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(FIND_ALL_BOOK_SQL, bookRowMapper);
    }

    @Override
    public List<Book> findAll(String sub) {
        return jdbcTemplate.query(FIND_ALL_BOOK_CONTAIN_SUB_SQL, bookRowMapper, "%" + sub + "%");
    }

    @Override
    public void deleteById(Long id) {
        Book book = findById(id);
        if (book == null) {
            return;
        }

        Integer status = jdbcTemplate.update(DELETE_BOOK_SQL, id);
    }

}
