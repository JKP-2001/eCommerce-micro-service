package com.org.microservice.product.dto;

import java.math.BigDecimal;

public record ProductResponse(
        int id,
        String name,
        String description,
        Double availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
