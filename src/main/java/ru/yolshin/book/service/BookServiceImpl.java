package ru.yolshin.book.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.yolshin.book.dao.BookDAO;
import ru.yolshin.book.entity.Book;

import java.util.*;
import java.util.stream.Collectors;


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
    public Map<String, List<Book>> groupByAuthor() {
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

    @Override
    public Map<String, Integer> findAuthor(String sub) {
        Map<String, Integer> bookMap = new HashMap<>();

        bookDAO.findAll(sub)
                .forEach(book -> {
                    Integer count = count(book.getTitle().toLowerCase(), sub.toLowerCase());
                    if (bookMap.containsKey(book.getAuthor())) {
                        Integer countOld = bookMap.get(book.getAuthor());

                        bookMap.put(book.getAuthor(), countOld + count);
                    } else {
                        bookMap.put(book.getAuthor(), count);
                    }
                });

        return bookMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Integer count(String value, String sub) {
        return StringUtils.countOccurrencesOf(value, sub);
    }

}
