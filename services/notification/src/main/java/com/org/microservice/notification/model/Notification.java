package com.org.microservice.notification.model;

import com.org.microservice.notification.kafka.order.OrderConfirmation;
import com.org.microservice.notification.kafka.payment.PaymentNotificationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType notificationType;
    private LocalDateTime notificationTime;
    private OrderConfirmation orderConfirmation;
    private PaymentNotificationRequest paymentConfirmation;
}
