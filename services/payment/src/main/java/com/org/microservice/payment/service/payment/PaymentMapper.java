package com.org.microservice.payment.service.payment;

import com.org.microservice.payment.dto.payment.PaymentNotificationRequest;
import com.org.microservice.payment.dto.payment.PaymentRequest;
import com.org.microservice.payment.model.payment.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentRequest.id())
                .paymentMethod(paymentRequest.paymentMethod())
                .amount(paymentRequest.amount())
                .orderId(paymentRequest.orderId())
                .createdDate(LocalDateTime.now())
                .build();

    }
}
