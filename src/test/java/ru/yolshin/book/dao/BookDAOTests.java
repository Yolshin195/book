package ru.yolshin.book.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.yolshin.book.entity.Book;

import java.util.List;

import org.junit.jupiter.api.Assertions;

@SpringBootTest
public class BookDAOTests {

    @Autowired
    private BookDAO bookDAO;

    @Test
    void findByIdTest() {
        Assertions.assertNotNull(bookDAO.findById(5L));
    }

    @Test
    @Sql(statements = "delete from book where id = 20", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void createTest() {
        Book book = Book.builder()
                .id(20L)
                .title("C++ through Game Programming (2016)")
                .author("Michael Dawson")
                .description("If you want to learn how to program first-class games, you just need to learn the C++ ...")
                .build();

        bookDAO.create(book);

        Assertions.assertNotNull(bookDAO.findById(20L));
    }

    @Test
    void findAllTest() {
        List<Book> bookList = bookDAO.findAll();
        Assertions.assertEquals(bookList.size(), 5);
    }

    @Test
    void findAllSubTest() {
        String sub = "a";

        bookDAO.findAll(sub).forEach(book ->
                Assertions.assertTrue(book.getTitle().contains(sub))
        );
    }

}
