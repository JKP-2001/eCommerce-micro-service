package com.org.microservice.order.dto.order;

import com.org.microservice.order.model.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderResponse(
        Integer id,
        BigDecimal amount,
        String reference,
        PaymentMethod paymentMethod,
        String customerId
) {
}
