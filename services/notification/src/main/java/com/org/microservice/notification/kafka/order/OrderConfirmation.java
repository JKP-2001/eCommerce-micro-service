package com.org.microservice.notification.kafka.order;

import com.org.microservice.notification.dto.CustomerResponse;
import com.org.microservice.notification.dto.PaymentMethod;
import com.org.microservice.notification.dto.ProductPurchaseResponse;


import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<ProductPurchaseResponse> products
) {
}

