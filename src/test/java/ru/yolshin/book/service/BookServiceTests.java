package ru.yolshin.book.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.junit.jupiter.api.Assertions;
import ru.yolshin.book.entity.Book;

import java.util.List;
import java.util.Map;


@SpringBootTest
public class BookServiceTests {
    @Autowired
    BookService bookService;

    @Test
    void findAllTest() {
        List<Book> bookList = bookService.findAll();
        Assertions.assertEquals(bookList.size(), 5);
    }

    @Test
    void groupByAuthorTest() {
        Map<String, List<Book>> mapBook = bookService.groupByAuthor();

        Assertions.assertEquals(mapBook.get("L. Tolstoy").size(), 2);
    }

    @Test
    void findAuthorTest() {
        Map<String, Integer> mapAuthor = bookService.findAuthor("a");
        Assertions.assertEquals(mapAuthor.get("L. Tolstoy"), 7);
    }

    @Test
    @Sql(statements = "delete from book where id = 30", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void createTest() {
        Book book = Book.builder()
                .id(30L)
                .title("C++ through Game Programming (2016)")
                .author("Michael Dawson")
                .description("If you want to learn how to program first-class games, you just need to learn the C++ ...")
                .build();

        Book bookDb = bookService.create(book);

        Assertions.assertEquals(book.getTitle(), bookDb.getTitle());
    }
}
