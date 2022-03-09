package com.getir.bookstore.service;

import com.getir.bookstore.web.model.dto.OrderDto;

import java.math.BigDecimal;
import java.util.List;

public interface CalculateStatistic {
    BigDecimal calculateMonthlyPurchasedAmount(List<OrderDto> orders);

    int  calculateMonthlyOrderCount(List<OrderDto> orders);

    int calculateMonthlyBookCount(List<OrderDto> orders);
}
