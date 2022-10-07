package ru.yolshin.book.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yolshin.book.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Book.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .author(rs.getString("author"))
                .description(rs.getString("description"))
                .build();
    }
}
