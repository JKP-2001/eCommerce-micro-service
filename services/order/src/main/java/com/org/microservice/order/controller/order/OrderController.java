package com.org.microservice.order.controller.order;

import com.org.microservice.order.dto.order.OrderRequest;
import com.org.microservice.order.dto.order.OrderResponse;
import com.org.microservice.order.service.order.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @CircuitBreaker(name="payment-and-product-service", fallbackMethod = "fallBackMethod1")
    public ResponseEntity<Integer> create(@RequestBody @Valid final OrderRequest orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") final Integer orderId) {
        return ResponseEntity.ok(orderService.findById(orderId));
    }

    public ResponseEntity<Integer> fallBackMethod1(@RequestBody @Valid final OrderRequest orderRequest, Throwable throwable){
        System.out.println("Fallback executed due to: " + throwable.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
}
