package ru.clevertec.exception;

public class NewsNotFoundException extends Exception {

    public NewsNotFoundException(String cause) {
        super(cause);
    }
}