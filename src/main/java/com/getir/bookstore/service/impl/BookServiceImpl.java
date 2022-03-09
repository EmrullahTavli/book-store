package com.getir.bookstore.service.impl;

import com.getir.bookstore.web.exception.BookStoreException;
import com.getir.bookstore.entity.Book;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.service.BookService;
import com.getir.bookstore.web.mapper.BookMapper;
import com.getir.bookstore.web.model.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Override
    public BookDto saveBook(BookDto bookDto) {
        bookRepository.findBookBy(bookMapper.mapCriteria(bookDto))
                .ifPresent(result -> {
                    throw new BookStoreException("Book is already exist.");
                });

        Book book = bookRepository.save(bookMapper.map(bookDto));
        return bookMapper.map(book);
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        BookDto foundBook = getBookByCriteria(bookDto);
        Book book = bookMapper.map(foundBook);
        book.setQuantityOnHand(bookDto.getQuantityOnHand());

        Book updatedBook = bookRepository.save(book);
        return bookMapper.map(updatedBook);
    }

    @Override
    public BookDto getBookByCriteria(BookDto bookDto) {
        Book book = bookRepository.findBookBy(bookMapper.mapCriteria(bookDto))
                .orElseThrow(() -> new BookStoreException("Book couldn't be found."));

        return bookMapper.map(book);
    }
}
