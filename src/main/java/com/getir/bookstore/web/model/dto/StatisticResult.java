package com.getir.bookstore.web.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Month;
import java.util.Map;

@Data
@Builder
public class StatisticResult {
    private Map<Month, StatisticDto> monthStatistics;
}
