package com.org.microservice.order.dto.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(
    @NotNull(message = "required a product id")
    Integer productId,
    @Positive(message = "quantity is mendatory")
    double quantity
){}
