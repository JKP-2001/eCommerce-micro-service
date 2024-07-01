package com.org.microservice.order.repository.orderline;

import com.org.microservice.order.dto.orderline.OrderLineResponse;
import com.org.microservice.order.model.orderline.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
