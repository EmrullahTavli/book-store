package com.getir.bookstore.service.impl;

import com.getir.bookstore.service.CalculateStatistic;
import com.getir.bookstore.web.model.dto.BookDto;
import com.getir.bookstore.web.model.dto.OrderDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Component
public class CalculateStatisticImpl implements CalculateStatistic {
    @Override
    public BigDecimal calculateMonthlyPurchasedAmount(List<OrderDto> orders) {
        return orders.stream()
                .map(OrderDto::getBooks)
                .flatMap(Collection::stream)
                .map(BookDto::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int calculateMonthlyOrderCount(List<OrderDto> orders) {
        return orders.size();
    }

    @Override
    public int calculateMonthlyBookCount(List<OrderDto> orders) {
        return (int) orders.stream()
                .map(OrderDto::getBooks)
                .mapToLong(Collection::size)
                .sum();
    }
}
