package com.getir.bookstore.web.controller;

import com.getir.bookstore.service.BookService;
import com.getir.bookstore.web.model.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public BookDto saveBook(@Valid @RequestBody BookDto book) {
        return bookService.saveBook(book);
    }

    @PutMapping
    public BookDto updateBook(@Valid @RequestBody BookDto book) {
        return bookService.updateBook(book);
    }
}
