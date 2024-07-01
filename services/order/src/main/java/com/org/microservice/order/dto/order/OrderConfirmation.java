package com.org.microservice.order.dto.order;

import com.org.microservice.order.dto.customer.CustomerResponse;
import com.org.microservice.order.dto.product.ProductPurchaseResponse;
import com.org.microservice.order.model.PaymentMethod;

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
