package com.getir.bookstore.service;

import com.getir.bookstore.web.model.dto.CustomerDto;

public interface CustomerService {
    CustomerDto getCustomer(String email);

    CustomerDto saveCustomer(CustomerDto customer);

    CustomerDto getCustomerById(String customerId);
}
