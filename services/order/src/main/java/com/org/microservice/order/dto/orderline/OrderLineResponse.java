package com.org.microservice.order.dto.orderline;

import lombok.Builder;

@Builder
public record OrderLineResponse(
        Integer id,
        double quantity
) {
}
