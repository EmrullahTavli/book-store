package com.getir.bookstore.web.mapper;

import com.getir.bookstore.entity.Book;
import com.getir.bookstore.entity.Customer;
import com.getir.bookstore.entity.Order;
import com.getir.bookstore.web.model.dto.BookDto;
import com.getir.bookstore.web.model.dto.CustomerDto;
import com.getir.bookstore.web.model.dto.OrderDto;
import com.getir.bookstore.web.model.enumeration.Status;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderMapperTest {
    private static final String FULL_NAME = "Emrullah Tavli";
    private static final String EMAIL = "emrullahtavli@gmail.com";
    private static final String BOOK_NAME = "Origin";
    private static final String PUBLISHER = "Doubleday";
    private static final String AUTHOR = "Dan Brown";
    private static final List<String> AUTHORS = Collections.singletonList(AUTHOR);

    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    @Test
    void orderDtoToOrder() {
        //given
        OrderDto orderDto = OrderDto.builder()
                .books(Collections.singletonList(
                        BookDto.builder()
                                .name(BOOK_NAME)
                                .price(BigDecimal.valueOf(10.50))
                                .authors(AUTHORS)
                                .publisher(PUBLISHER)
                                .build()))
                .customer(CustomerDto.builder()
                        .fullName(FULL_NAME)
                        .email(EMAIL)
                        .build())
                .status(Status.ORDERED.name())
                .build();

        //when
        Order result = orderMapper.map(orderDto);

        //then
        assertThat(result.getBooks(), Matchers.<Collection<Book>>allOf(hasSize(1)));
        assertEquals(Status.ORDERED.name(), result.getStatus());
    }

    @Test
    void orderToOrderDto() {
        Order order = Order.builder()
                .books(Collections.singletonList(
                        Book.builder()
                                .name(BOOK_NAME)
                                .price(BigDecimal.valueOf(10.50))
                                .authors(AUTHORS)
                                .publisher(PUBLISHER)
                                .build()))
                .customer(Customer.builder()
                        .fullName(FULL_NAME)
                        .email(EMAIL)
                        .build())
                .status(Status.ORDERED.name())
                .build();

        //when
        OrderDto result = orderMapper.map(order);

        //then
        assertThat(result.getBooks(), Matchers.<Collection<BookDto>>allOf(hasSize(1)));
        assertEquals(Status.ORDERED.name(), result.getStatus());
    }
}