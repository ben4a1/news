package ru.clevertec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.dto.Response;


import static java.time.LocalDateTime.now;

@RestControllerAdvice
public class RestAdvice {

    @ExceptionHandler({NewsNotFoundException.class, CommentNotFoundException.class})
    public ResponseEntity<Response> handleException(Exception exception) {
        String message = String.format("%s %s", now(), exception.getMessage());
        Response response = new Response(message);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}