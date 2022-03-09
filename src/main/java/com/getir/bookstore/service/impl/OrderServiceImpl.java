package com.getir.bookstore.service.impl;

import com.getir.bookstore.entity.Customer;
import com.getir.bookstore.entity.Order;
import com.getir.bookstore.repository.OrderRepository;
import com.getir.bookstore.service.BookService;
import com.getir.bookstore.service.CustomerService;
import com.getir.bookstore.service.OrderService;
import com.getir.bookstore.web.exception.BookStoreException;
import com.getir.bookstore.web.mapper.CustomerMapper;
import com.getir.bookstore.web.mapper.OrderMapper;
import com.getir.bookstore.web.model.dto.BookDto;
import com.getir.bookstore.web.model.dto.CustomerDto;
import com.getir.bookstore.web.model.dto.OrderDto;
import com.getir.bookstore.web.model.dto.OrderPagedList;
import com.getir.bookstore.web.model.enumeration.Status;
import com.getir.bookstore.web.model.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final BookService bookService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    @Override
    public OrderPagedList getCustomerOrders(String customerId, OrderRequest request) {
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        Customer customer = customerMapper.map(customerDto);
        Page<Order> orders = orderRepository.findAllByCustomer(customer,
                PageRequest.of(request.getPage(), request.getLimit()));
        return build(orders);
    }

    @Override
    public OrderDto getOrder(String customerId, String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new BookStoreException("Order couldn't be found."));

        checkOrderBelongToCustomer(customerId, order);

        return orderMapper.map(order);
    }

    @Override
    public OrderDto saveOrder(String customerId, OrderDto orderDto) {
        CustomerDto customer = customerService.getCustomerById(customerId);
        orderDto.setCustomer(customer);

        List<BookDto> books = getBooksByCriteria(orderDto);
        orderDto.setBooks(books);
        checkBookStockAndUpdateStock(books);
        orderDto.setStatus(Status.ORDERED.name());

        Order order = orderRepository.save(orderMapper.map(orderDto));
        return orderMapper.map(order);
    }

    private void checkBookStockAndUpdateStock(List<BookDto> books) {
        books.forEach(book -> {
            if (book.getQuantityOnHand() < 1) {
                log.error("No book found in stock. book: {} ", book.getName());
                throw new BookStoreException("No book found in stock.");
            }
            book.setQuantityOnHand(book.getQuantityOnHand() - 1);
        });

        books.forEach(bookService::updateBook);
    }

    private List<BookDto> getBooksByCriteria(OrderDto orderDto) {
        return orderDto.getBooks().stream()
                .map(bookService::getBookByCriteria)
                .collect(Collectors.toList());
    }

    private void checkOrderBelongToCustomer(String customerId, Order order) {
        if (!order.getCustomer().getId().equals(customerId)) {
            throw new BookStoreException("Order doesn't belong to the customer.");
        }
    }

    private OrderPagedList build(Page<Order> orders) {
        return orders.getContent().size() > 0 ?
                OrderPagedList.builder()
                        .content(orders.stream()
                                .map(orderMapper::map)
                                .collect(Collectors.toList()))
                        .pageable(PageRequest.of(orders.getPageable().getPageNumber(),
                                orders.getPageable().getPageSize()))
                        .total(orders.getTotalElements())
                        .build() : null;
    }
}
