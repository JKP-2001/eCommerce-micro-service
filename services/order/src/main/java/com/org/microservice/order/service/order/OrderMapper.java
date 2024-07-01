package com.org.microservice.order.service.order;

import com.org.microservice.order.dto.order.OrderRequest;
import com.org.microservice.order.dto.order.OrderResponse;
import com.org.microservice.order.model.order.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderMapper {

    public Order mapToOrder(OrderRequest orderRequest) {
        return Order.builder()
                .id(orderRequest.id())
                .customerId(orderRequest.customerId())
                .reference(orderRequest.reference())
                .totalAmount(orderRequest.totalAmount())
                .paymentMethod(orderRequest.paymentMethod())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .reference(order.getReference())
                .amount(order.getTotalAmount())
                .paymentMethod(order.getPaymentMethod())
                .customerId(order.getCustomerId())
                .build();
    }
}
