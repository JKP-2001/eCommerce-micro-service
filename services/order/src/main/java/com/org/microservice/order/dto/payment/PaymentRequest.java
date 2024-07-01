package com.org.microservice.order.dto.payment;

import com.org.microservice.order.dto.customer.CustomerResponse;
import com.org.microservice.order.model.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
