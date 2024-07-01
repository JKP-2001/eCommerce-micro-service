package com.org.microservice.order.service.payment;

import com.org.microservice.order.dto.payment.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name="payment-service",
        url="${application.config.payment-url}"
)
public interface PaymentService {
    @PostMapping
    Integer createPayment(@RequestBody PaymentRequest paymentRequest);
}
