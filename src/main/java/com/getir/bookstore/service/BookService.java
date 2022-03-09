package com.getir.bookstore.service;

import com.getir.bookstore.web.model.dto.BookDto;

public interface BookService {
    BookDto saveBook(BookDto book);

    BookDto updateBook(BookDto book);

    BookDto getBookByCriteria(BookDto book);
}
