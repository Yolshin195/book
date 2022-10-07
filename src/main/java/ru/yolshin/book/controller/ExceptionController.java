package ru.yolshin.book.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yolshin.book.model.ErrorResponse;

import static org.slf4j.LoggerFactory.getLogger;

@Hidden
@RestControllerAdvice(annotations = RestController.class)
public class ExceptionController {
    private static final Logger logger = getLogger(ExceptionController.class);

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateKeyException.class)
    public ErrorResponse conflict(DuplicateKeyException exception) {
        logger.error("", exception);
        return new ErrorResponse(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse error(RuntimeException exception) {
        logger.error("", exception);
        return new ErrorResponse(exception.getMessage());
    }
}
