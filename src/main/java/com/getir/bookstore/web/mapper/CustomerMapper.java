package com.getir.bookstore.web.mapper;

import com.getir.bookstore.entity.Customer;
import com.getir.bookstore.web.model.dto.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto map(Customer customer);

    Customer map(CustomerDto customerDto);
}
