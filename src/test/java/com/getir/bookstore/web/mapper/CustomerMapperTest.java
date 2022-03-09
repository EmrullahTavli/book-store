package com.getir.bookstore.web.mapper;

import com.getir.bookstore.entity.Customer;
import com.getir.bookstore.web.model.dto.CustomerDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {
    private static final String FULL_NAME = "Emrullah Tavli";
    private static final String EMAIL = "emrullahtavli@gmail.com";

    private static final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    void customerDtoToCustomer() {
        //given
        CustomerDto customer = CustomerDto.builder()
                .fullName(FULL_NAME)
                .email(EMAIL)
                .build();

        //when
        Customer result = customerMapper.map(customer);

        //then
        assertEquals(FULL_NAME, result.getFullName());
        assertEquals(EMAIL, result.getEmail());
    }

    @Test
    void customerToCustomerDto() {
        //given
        Customer customer = Customer.builder()
                .fullName(FULL_NAME)
                .email(EMAIL)
                .build();

        //when
        CustomerDto result = customerMapper.map(customer);

        //then
        assertEquals(FULL_NAME, result.getFullName());
        assertEquals(EMAIL, result.getEmail());
    }
}