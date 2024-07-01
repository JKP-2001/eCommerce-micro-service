package com.org.microservice.customer.controller;

import com.org.microservice.customer.dto.CustomerRequest;
import com.org.microservice.customer.dto.CustomerResponse;
import com.org.microservice.customer.model.Customer;
import com.org.microservice.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        String response = customerService.create(customerRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        String response = customerService.update(customerRequest);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.findAll());
    }
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }
}
