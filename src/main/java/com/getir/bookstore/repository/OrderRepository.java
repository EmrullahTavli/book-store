package com.getir.bookstore.repository;

import com.getir.bookstore.entity.Customer;
import com.getir.bookstore.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface OrderRepository extends MongoRepository<Order, String> {
    Page<Order> findAllByCustomer(Customer customer, Pageable pageable);

    Page<Order> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
