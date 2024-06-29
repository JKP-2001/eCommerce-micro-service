package com.org.microservice.customer.dto;

import com.org.microservice.customer.model.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastname;
    private String email;
    private Address address;
}
