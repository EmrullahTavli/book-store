package com.getir.bookstore.web.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StatisticDto {
    private int orderCount;
    private int bookCount;
    private BigDecimal purchasedAmount;
}
