package ru.yolshin.book.book.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.yolshin.book.book.DAO.BookDAO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.StringContains.containsString;


@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/book")).andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void getAllTest() throws Exception {
            this.mockMvc.perform(get("/api/v1/book")).andDo(print())
                    .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void groupByAuthorTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/book/groupBy/author")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void symbolTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/book/symbol/a")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    @Sql(statements = "delete from book where id = 11", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void createTest() throws Exception {
        String body = """
                {
                  "id": 11,
                  "title": "C++ through Game Programming (2016)",
                  "author": "Michael Dawson",
                  "description": "If you want to learn how to program first-class games, you just need to learn the C++ ..."
                }
                """;

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/book")
                .accept(MediaType.APPLICATION_JSON)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("11")));
    }

}
