package com.getir.bookstore.service.impl;

import com.getir.bookstore.entity.Customer;
import com.getir.bookstore.repository.CustomerRepository;
import com.getir.bookstore.web.exception.BookStoreException;
import com.getir.bookstore.web.model.dto.CustomerDto;
import com.getir.bookstore.web.mapper.CustomerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
    private static final String ID = UUID.randomUUID().toString();
    private static final String FULL_NAME = "Emrullah Tavli";
    private static final String EMAIL = "emrullahtavli@gmail.com";

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void getCustomerByEmail_shouldSuccess() {
        //given
        CustomerDto customerDto = CustomerDto.builder()
                .fullName(FULL_NAME)
                .email(EMAIL)
                .build();

        Customer customer = Customer.builder()
                .fullName(FULL_NAME)
                .email(EMAIL)
                .build();

        given(customerRepository.findCustomerByEmail(EMAIL)).willReturn(Optional.ofNullable(customer));
        given(customerMapper.map(customer)).willReturn(customerDto);

        //when
        CustomerDto result = customerService.getCustomer(EMAIL);

        //then
        assertEquals(FULL_NAME, result.getFullName());
        assertEquals(EMAIL, result.getEmail());
    }

    @Test
    void getCustomerByEmail_shouldFail() {
        //given
        given(customerRepository.findCustomerByEmail(EMAIL)).willReturn(Optional.empty());

        //when
        //then
        Throwable throwable = catchThrowable(() -> customerService.getCustomer(EMAIL));
        assertNotNull(throwable);
        assertTrue(throwable instanceof BookStoreException);
        assertEquals("Customer couldn't be found.", throwable.getMessage());
    }

    @Test
    void saveCustomer_shouldSuccess() {
        //given
        CustomerDto customerDto = CustomerDto.builder()
                .id(ID)
                .fullName(FULL_NAME)
                .email(EMAIL)
                .build();

        Customer customer = Customer.builder()
                .id(ID)
                .fullName(FULL_NAME)
                .email(EMAIL)
                .build();

        given(customerRepository.findCustomerByEmail(EMAIL)).willReturn(Optional.empty());
        given(customerMapper.map(customerDto)).willReturn(customer);
        given(customerRepository.save(customer)).willReturn(customer);
        given(customerMapper.map(customer)).willReturn(customerDto);

        //when
        CustomerDto result = customerService.saveCustomer(customerDto);

        //then
        verify(customerRepository).save(ArgumentMatchers.argThat(type -> {
            assertEquals(customer, type);
            return true;
        }));

        assertEquals(customerDto, result);
    }

    @Test
    void saveCustomer_shouldFail_customerAlreadyExist() {
        //given
        CustomerDto customerDto = CustomerDto.builder()
                .fullName(FULL_NAME)
                .email(EMAIL)
                .build();

        Customer customer = Customer.builder()
                .fullName(FULL_NAME)
                .email(EMAIL)
                .build();

        given(customerRepository.findCustomerByEmail(EMAIL)).willReturn(Optional.ofNullable(customer));

        //when
        //then
        Throwable throwable = catchThrowable(() -> customerService.saveCustomer(customerDto));
        assertNotNull(throwable);
        assertTrue(throwable instanceof BookStoreException);
        assertEquals("Customer is already exist.", throwable.getMessage());
    }
}