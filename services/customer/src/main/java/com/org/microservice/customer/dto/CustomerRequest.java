package com.org.microservice.customer.dto;

import com.org.microservice.customer.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstname required")
        String firstName,
        @NotNull(message = "Customer lastname required")
        String lastname,
        @NotNull(message = "Customer email required")
        @Email(message = "Invalid email")
        String email,
        Address address

) {

}
