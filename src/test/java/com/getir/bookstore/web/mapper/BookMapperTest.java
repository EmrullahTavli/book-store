package com.getir.bookstore.web.mapper;

import com.getir.bookstore.entity.Book;
import com.getir.bookstore.web.model.dto.BookDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BookMapperTest {
    private static final String BOOK_NAME = "Origin";
    private static final String PUBLISHER = "Doubleday";
    private static final String AUTHOR = "Dan Brown";
    private static final List<String> AUTHORS = Collections.singletonList(AUTHOR);

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Test
    void bookDtoToBook() {
        //given
        BookDto bookDto = BookDto.builder()
                .name(BOOK_NAME)
                .price(BigDecimal.valueOf(10.50))
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .build();

        //when
        Book result = bookMapper.map(bookDto);

        //then
        assertEquals(BOOK_NAME, result.getName());
        assertEquals(PUBLISHER, result.getPublisher());
        assertThat(BigDecimal.valueOf(10.50), Matchers.comparesEqualTo(result.getPrice()));
        assertThat(AUTHORS, Matchers.<Collection<String>>allOf(
                hasSize(1),
                hasItem(is(AUTHOR)))
        );
    }

    @Test
    void bookToBookDto() {
        Book book = Book.builder()
                .name(BOOK_NAME)
                .price(BigDecimal.valueOf(10.50))
                .authors(AUTHORS)
                .publisher(PUBLISHER)
                .build();

        //when
        BookDto result = bookMapper.map(book);

        //then
        assertEquals(BOOK_NAME, result.getName());
        assertEquals(PUBLISHER, result.getPublisher());
        assertThat(BigDecimal.valueOf(10.50), Matchers.comparesEqualTo(result.getPrice()));
        assertThat(AUTHORS, Matchers.<Collection<String>>allOf(
                hasSize(1),
                hasItem(is(AUTHOR)))
        );
    }
}