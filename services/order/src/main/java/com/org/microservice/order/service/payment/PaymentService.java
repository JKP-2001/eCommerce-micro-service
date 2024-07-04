package com.org.microservice.order.service.payment;

import com.org.microservice.order.dto.payment.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name="payment-service",
        url="${application.config.payment-url}"
)
public interface PaymentService {
    @PostMapping
    Integer createPayment(@RequestHeader(value = "Authorization", required = true) String token ,@RequestBody PaymentRequest paymentRequest);
}
