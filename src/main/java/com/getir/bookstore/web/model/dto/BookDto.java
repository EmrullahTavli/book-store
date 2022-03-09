package com.getir.bookstore.web.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class BookDto {
    private String id;
    @NotBlank
    private String name;

    @NotNull
    private List<String> authors;

    @NotBlank
    private String publisher;

    @DecimalMin(value = "0.01", inclusive = false)
    private BigDecimal price;

    @PositiveOrZero
    private int quantityOnHand;
}
