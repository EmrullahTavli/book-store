package com.getir.bookstore.service;

import com.getir.bookstore.web.model.dto.OrderDto;
import com.getir.bookstore.web.model.dto.OrderPagedList;
import com.getir.bookstore.web.model.request.OrderRequest;

public interface OrderService {
    OrderDto getOrder(String customerId, String orderId);

    OrderPagedList getCustomerOrders(String customerId, OrderRequest request);

    OrderDto saveOrder(String customerId, OrderDto order);
}
