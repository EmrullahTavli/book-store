package com.getir.bookstore.web.controller;

import com.getir.bookstore.web.model.dto.CustomerDto;
import com.getir.bookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public CustomerDto getCustomer(@RequestParam(value = "email", required = false) String email) {
        return customerService.getCustomer(email);
    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomerById(@PathVariable String customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public CustomerDto saveCustomer(@Valid @RequestBody CustomerDto customer) {
        return customerService.saveCustomer(customer);
    }
}
