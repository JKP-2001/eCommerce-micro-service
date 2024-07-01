package com.org.microservice.order.dto.order;

import com.org.microservice.order.dto.product.PurchaseRequest;
import com.org.microservice.order.model.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;


public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "amount should be positive")
        BigDecimal totalAmount,
        @NotNull(message = "payment method should be precise")
        PaymentMethod paymentMethod,
        @NotNull(message = "customer is required")
        @NotEmpty(message = "customer is required")
        @NotBlank(message = "customer is required")
        String customerId,
        @NotEmpty(message = "you should purchase one product")
        List<PurchaseRequest> products
) {
}
