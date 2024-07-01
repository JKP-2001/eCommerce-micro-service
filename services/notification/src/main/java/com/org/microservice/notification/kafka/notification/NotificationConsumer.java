package com.org.microservice.notification.kafka.notification;

import com.org.microservice.notification.kafka.order.OrderConfirmation;
import com.org.microservice.notification.kafka.payment.PaymentNotificationRequest;
import com.org.microservice.notification.model.Notification;
import com.org.microservice.notification.repository.notification.NotificationRepository;
import com.org.microservice.notification.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.org.microservice.notification.model.NotificationType.PAYMENT_CONFIRMATION;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic", groupId = "paymentGroup")
    public void consumePaymentSuccessNotification(PaymentNotificationRequest paymentNotificationRequest) throws MessagingException {
        log.info(String.format("Consuming the message from payment topic:: %s", paymentNotificationRequest));
        notificationRepository.save(
                Notification.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                        .notificationTime(LocalDateTime.now())
                        .paymentConfirmation(paymentNotificationRequest)
                        .build()
        );
        var customerName = paymentNotificationRequest.customerFirstName()+" "+paymentNotificationRequest.customerLastName();
        var destinationEmail = paymentNotificationRequest.customerEmail();

        emailService.sendPaymentSuccessEmail(
                destinationEmail,
                customerName,
                paymentNotificationRequest.amount(),
                paymentNotificationRequest.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic", groupId = "orderGroup")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(String.format("Consuming the message from order topic:: %s", orderConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .notificationType(PAYMENT_CONFIRMATION)
                        .notificationTime(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        var customerName = orderConfirmation.customer().getFirstName()+" "+orderConfirmation.customer().getLastname();
        var destinationEmail = orderConfirmation.customer().getEmail();



        emailService.sendOrderConfirmationEmail(
                destinationEmail,
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );
    }

}
