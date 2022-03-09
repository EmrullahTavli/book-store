package com.getir.bookstore.service.impl;

import com.getir.bookstore.entity.Book;
import com.getir.bookstore.web.exception.BookStoreException;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.web.mapper.BookMapper;
import com.getir.bookstore.web.model.dto.BookCriteria;
import com.getir.bookstore.web.model.dto.BookDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    private static final String ID = UUID.randomUUID().toString();
    private static final String BOOK_NAME = "Origin";
    private static final String PUBLISHER = "Doubleday";
    private static final String AUTHOR = "Dan Brown";
    private static final List<String> AUTHORS = Collections.singletonList(AUTHOR);

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void saveBook_shouldSuccess() {
        //given
        BookDto bookDto = BookDto.builder()
                .name(BOOK_NAME)
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .quantityOnHand(10)
                .price(BigDecimal.valueOf(10.50))
                .build();

        Book book = Book.builder()
                .id(ID)
                .name(BOOK_NAME)
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .quantityOnHand(10)
                .price(BigDecimal.valueOf(10.50))
                .build();

        BookCriteria bookCriteria = BookCriteria.builder()
                .name(BOOK_NAME)
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .build();

        given(bookMapper.mapCriteria(bookDto)).willReturn(bookCriteria);
        given(bookRepository.findBookBy(bookCriteria)).willReturn(Optional.empty());
        given(bookMapper.map(bookDto)).willReturn(book);
        given(bookRepository.save(book)).willReturn(book);
        given(bookMapper.map(book)).willReturn(bookDto);

        //when
        BookDto result = bookService.saveBook(bookDto);

        //then
        verify(bookRepository).save(ArgumentMatchers.argThat(type -> {
            assertEquals(book, type);
            return true;
        }));

        assertEquals(bookDto, result);
    }

    @Test
    void saveCustomer_shouldFail_bookAlreadyExist() {
        //given
        BookDto bookDto = BookDto.builder()
                .name(BOOK_NAME)
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .quantityOnHand(10)
                .price(BigDecimal.valueOf(10.50))
                .build();

        Book book = Book.builder()
                .id(ID)
                .name(BOOK_NAME)
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .quantityOnHand(10)
                .price(BigDecimal.valueOf(10.50))
                .build();

        BookCriteria bookCriteria = BookCriteria.builder()
                .name(BOOK_NAME)
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .build();

        given(bookMapper.mapCriteria(bookDto)).willReturn(bookCriteria);
        given(bookRepository.findBookBy(bookCriteria)).willReturn(Optional.ofNullable(book));

        //when
        //then
        Throwable throwable = catchThrowable(() -> bookService.saveBook(bookDto));
        assertNotNull(throwable);
        assertTrue(throwable instanceof BookStoreException);
        assertEquals("Book is already exist.", throwable.getMessage());
    }

    @Test
    void updateBook_shouldSuccess() {
        //given
        BookDto bookDto = BookDto.builder()
                .id(ID)
                .name(BOOK_NAME)
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .quantityOnHand(10)
                .price(BigDecimal.valueOf(10.50))
                .build();

        Book book = Book.builder()
                .id(ID)
                .name(BOOK_NAME)
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .quantityOnHand(10)
                .price(BigDecimal.valueOf(10.50))
                .build();

        BookCriteria bookCriteria = BookCriteria.builder()
                .name(BOOK_NAME)
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .build();

        given(bookMapper.mapCriteria(bookDto)).willReturn(bookCriteria);
        given(bookRepository.findBookBy(bookCriteria)).willReturn(Optional.ofNullable(book));
        given(bookMapper.map(bookDto)).willReturn(book);
        given(bookRepository.save(any(Book.class))).willReturn(book);
        given(bookMapper.map(book)).willReturn(bookDto);

        //when
        BookDto result = bookService.updateBook(bookDto);

        //then
        verify(bookRepository).save(ArgumentMatchers.argThat(type -> {
            assertEquals(book, type);
            return true;
        }));

        assertEquals(bookDto, result);
    }

    @Test
    void updateBook_shouldFail_bookNotFound() {
        //given
        BookDto bookDto = BookDto.builder()
                .name(BOOK_NAME)
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .quantityOnHand(10)
                .price(BigDecimal.valueOf(10.50))
                .build();

        BookCriteria bookCriteria = BookCriteria.builder()
                .name(BOOK_NAME)
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .build();

        given(bookMapper.mapCriteria(bookDto)).willReturn(bookCriteria);
        given(bookRepository.findBookBy(bookCriteria)).willReturn(Optional.empty());

        //when
        //then
        Throwable throwable = catchThrowable(() -> bookService.updateBook(bookDto));
        assertNotNull(throwable);
        assertTrue(throwable instanceof BookStoreException);
        assertEquals("Book couldn't be found.", throwable.getMessage());
    }
}