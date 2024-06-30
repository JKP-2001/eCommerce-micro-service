package com.org.microservice.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public record ProductRequest(
        int id,
        @NotNull(message = "product name is required")
        String name,
        @NotNull(message = "product description is required")
        String description,
        @NotNull(message = "product quantity is required")
        @Positive(message = "quantity should be positive")
        Double availableQuantity,
        @NotNull(message = "product price is required")
        @Positive(message = "price should be positive")
        BigDecimal price,
        @NotNull(message = "product category is required")
        Integer categoryId
) {
}
