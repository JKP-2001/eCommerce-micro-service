package com.org.microservice.order.service.orderline;

import com.org.microservice.order.dto.orderline.OrderLineResponse;
import com.org.microservice.order.model.orderline.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return OrderLineResponse.builder()
                .id(orderLine.getId())
                .quantity(orderLine.getQuantity())
                .build();
    }
}
