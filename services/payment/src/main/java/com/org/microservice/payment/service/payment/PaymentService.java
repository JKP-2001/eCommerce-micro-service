package com.org.microservice.payment.service.payment;

import com.org.microservice.payment.dto.payment.PaymentNotificationRequest;
import com.org.microservice.payment.dto.payment.PaymentRequest;
import com.org.microservice.payment.kafka.payment.PaymentNotificationProducer;
import com.org.microservice.payment.model.payment.Payment;
import com.org.microservice.payment.repository.payment.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentNotificationProducer paymentNotificationProducer;

    public Integer createPayment(PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.save(paymentMapper.toPayment(paymentRequest));
        paymentNotificationProducer.sendPaymentNotification(
                PaymentNotificationRequest.builder()
                        .orderReference(paymentRequest.reference())
                        .paymentMethod(paymentRequest.paymentMethod())
                        .amount(paymentRequest.amount())
                        .customerEmail(paymentRequest.customer().email())
                        .customerFirstName(paymentRequest.customer().firstName())
                        .customerLastName(paymentRequest.customer().lastName())
                        .build()
        );
        return payment.getId();
    }
}
