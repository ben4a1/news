package ru.clevertec.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such News")
public class NewsNotFoundException extends RuntimeException {

    public NewsNotFoundException() {
        super();
    }
}