package com.getir.bookstore.service.impl;

import com.getir.bookstore.service.CalculateStatistic;
import com.getir.bookstore.service.OrderService;
import com.getir.bookstore.service.StatisticService;
import com.getir.bookstore.web.model.dto.OrderDto;
import com.getir.bookstore.web.model.dto.OrderPagedList;
import com.getir.bookstore.web.model.dto.StatisticDto;
import com.getir.bookstore.web.model.dto.StatisticResult;
import com.getir.bookstore.web.model.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final OrderService orderService;
    private final CalculateStatistic calculateStatistic;

    @Override
    public StatisticResult getMonthlyStatisticsByCustomer(String customerId) {
        OrderPagedList orders = orderService.getCustomerOrders(customerId, buildOrderRequest());
        return getMonthlyStatistics(orders);
    }

    private StatisticResult getMonthlyStatistics(OrderPagedList orderPages) {
        Map<Month, List<OrderDto>> monthlyOrdersMap = orderPages.getContent().stream()
                .collect(groupingBy(order -> order.getCreatedAt().getMonth()));

        Map<Month, StatisticDto> monthlyStatistics = new HashMap<>();
        monthlyOrdersMap.forEach((month, orders) -> populateMonthlyStatistics(month, orders, monthlyStatistics));

        return StatisticResult.builder()
                .monthStatistics(monthlyStatistics)
                .build();
    }

    private OrderRequest buildOrderRequest() {
        return OrderRequest.builder()
                .page(0)
                .limit(Integer.MAX_VALUE)
                .build();
    }

    private void populateMonthlyStatistics(Month month, List<OrderDto> orders,
        Map<Month, StatisticDto> monthlyStatistics) {

        BigDecimal monthlyPurchasedAmount = calculateStatistic.calculateMonthlyPurchasedAmount(orders);
        int monthlyBookCount = calculateStatistic.calculateMonthlyBookCount(orders);
        int monthlyOrderCount = calculateStatistic.calculateMonthlyOrderCount(orders);

        StatisticDto statistic = StatisticDto.builder()
                .orderCount(monthlyOrderCount)
                .bookCount(monthlyBookCount)
                .purchasedAmount(monthlyPurchasedAmount)
                .build();

        monthlyStatistics.put(month, statistic);
    }
}
