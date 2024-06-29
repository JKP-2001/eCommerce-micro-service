package com.org.microservice.customer.service;

import com.org.microservice.customer.dto.CustomerRequest;
import com.org.microservice.customer.dto.CustomerResponse;
import com.org.microservice.customer.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .id(customerRequest.id())
                .email(customerRequest.email())
                .firstName(customerRequest.firstName())
                .lastname(customerRequest.lastname())
                .address(customerRequest.address())
                .build();
    }
    public CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastname(customer.getLastname())
                .address(customer.getAddress())
                .build();
    }
}
