package com.getir.bookstore.web.exception;

public class BookStoreException extends RuntimeException {
    public BookStoreException() {
        super();
    }

    public BookStoreException(String message) {
        super(message);
    }
}
