package ru.clevertec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Comment")
public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException() {
        super();
    }
}