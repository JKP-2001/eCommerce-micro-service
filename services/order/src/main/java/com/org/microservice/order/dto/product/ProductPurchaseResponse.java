package com.org.microservice.order.dto.product;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
public record ProductPurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
