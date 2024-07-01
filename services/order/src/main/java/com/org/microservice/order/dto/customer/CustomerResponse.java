package com.org.microservice.order.dto.customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private String id;
    private String firstName;
    private String lastname;
    private String email;
}

