package com.org.microservice.order.service.customer;

import com.org.microservice.order.dto.customer.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public interface CustomerService {

    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findCustomerById(@RequestHeader(value = "Authorization", required = true) String token, @PathVariable("customer-id") String customerId);
}
