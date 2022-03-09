package com.getir.bookstore.web.controller;

import com.getir.bookstore.web.endpoint.OrderEndpoint;
import com.getir.bookstore.web.model.dto.OrderDto;
import com.getir.bookstore.service.OrderService;
import com.getir.bookstore.web.model.dto.OrderPagedList;
import com.getir.bookstore.web.model.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping(OrderEndpoint.CUSTOMER_ORDER)
    public OrderDto getOrder(@PathVariable String customerId,
        @PathVariable String orderId) {
        return orderService.getOrder(customerId, orderId);
    }

    @GetMapping(OrderEndpoint.CUSTOMER_ORDERS)
    public OrderPagedList getCustomerOrders(@PathVariable("customerId") String customerId,
        @Valid @ModelAttribute OrderRequest request) {
        return orderService.getCustomerOrders(customerId, request);
    }

    @PostMapping(OrderEndpoint.CUSTOMER_ORDERS)
    public OrderDto saveOrder(@PathVariable String customerId,
        @Valid @RequestBody OrderDto order) {
        return orderService.saveOrder(customerId, order);
    }
}
