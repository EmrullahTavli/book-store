package com.getir.bookstore.web.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class OrderPagedList extends PageImpl<OrderDto> {
    @Builder
    public OrderPagedList(List<OrderDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public OrderPagedList(List<OrderDto> content) {
        super(content);
    }
}
