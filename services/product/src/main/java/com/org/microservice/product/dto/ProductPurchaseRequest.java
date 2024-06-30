package com.org.microservice.product.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message = "product id is required")
        Integer productId,
        @NotNull(message = "quantity is required")
        @Positive(message = "quantity must be positive")
        double quantity
) {
}
