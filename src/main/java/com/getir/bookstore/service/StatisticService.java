package com.getir.bookstore.service;

import com.getir.bookstore.web.model.dto.StatisticResult;

public interface StatisticService {
    StatisticResult getMonthlyStatisticsByCustomer(String customerId);
}
