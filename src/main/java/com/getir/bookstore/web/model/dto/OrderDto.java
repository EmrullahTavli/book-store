package com.getir.bookstore.web.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDto {
    private String id;

    private CustomerDto customer;

    @NotNull
    private List<BookDto> books;

    @NotBlank
    private String status;

    private LocalDateTime createdAt;
}
