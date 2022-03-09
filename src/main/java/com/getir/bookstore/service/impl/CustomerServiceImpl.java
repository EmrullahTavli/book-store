package com.getir.bookstore.service.impl;

import com.getir.bookstore.entity.Customer;
import com.getir.bookstore.repository.CustomerRepository;
import com.getir.bookstore.service.CustomerService;
import com.getir.bookstore.web.exception.BookStoreException;
import com.getir.bookstore.web.model.dto.CustomerDto;
import com.getir.bookstore.web.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDto getCustomer(String email) {
        Customer customer = customerRepository.findCustomerByEmail(email)
                .orElseThrow(() -> new BookStoreException("Customer couldn't be found."));

        return customerMapper.map(customer);
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        customerRepository.findCustomerByEmail(customerDto.getEmail())
                .ifPresent(customer -> {
                    throw new BookStoreException("Customer is already exist.");
                });

        Customer customer = customerRepository.save(customerMapper.map(customerDto));
        return customerMapper.map(customer);
    }

    @Override
    public CustomerDto getCustomerById(String customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new BookStoreException("Customer couldn't be found."));

        return customerMapper.map(customer);
    }
}
