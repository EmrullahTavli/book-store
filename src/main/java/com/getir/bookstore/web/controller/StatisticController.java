package com.getir.bookstore.web.controller;

import com.getir.bookstore.service.StatisticService;
import com.getir.bookstore.web.model.dto.StatisticResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/customers/{customerId}/monthlyStatistics")
    public StatisticResult getMonthlyStatisticByCustomer(@PathVariable String customerId) {
        return statisticService.getMonthlyStatisticsByCustomer(customerId);
    }
}
