package com.getir.bookstore.web.mapper;

import com.getir.bookstore.web.model.dto.OrderDto;
import com.getir.bookstore.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order map(OrderDto order);

    OrderDto map(Order order);

    List<OrderDto> map(List<Order> orders);
}
