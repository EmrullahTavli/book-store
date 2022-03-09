package com.getir.bookstore.web.endpoint;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderEndpoint {
    public static final String CUSTOMER_ORDERS = "/customers/{customerId}/orders";
    public static final String ORDERS = "/orders";
    public static final String CUSTOMER_ORDER = CUSTOMER_ORDERS + "/{orderId}";
}
