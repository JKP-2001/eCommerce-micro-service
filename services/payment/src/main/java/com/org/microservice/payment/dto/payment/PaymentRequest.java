package com.org.microservice.payment.dto.payment;

import com.org.microservice.payment.dto.customer.Customer;
import com.org.microservice.payment.model.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String reference,
        Customer customer
) {
}
