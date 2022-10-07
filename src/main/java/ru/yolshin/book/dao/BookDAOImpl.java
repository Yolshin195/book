package ru.yolshin.book.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.yolshin.book.entity.Book;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    private static final String CREATE_BOOK_SQL = "insert into BOOK (id, title, author, description) values (?, ?, ?, ?)";
    private static final String FIND_BY_ID_SQL = "select * from book where id = ?";
    private static final String FIND_ALL_BOOK_SQL = "select * from book order by title DESC";
    private static final String FIND_ALL_BOOK_CONTAIN_SUB_SQL = "select * from book where title like ?";
    private static final String DELETE_BOOK_SQL = "delete from book where id = ?";
    private static final String NEXT_ID_SQL = "select max(id)+1 from book";

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

    public Long nextId() {
        return jdbcTemplate.queryForObject(NEXT_ID_SQL, Long.class);
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

}
