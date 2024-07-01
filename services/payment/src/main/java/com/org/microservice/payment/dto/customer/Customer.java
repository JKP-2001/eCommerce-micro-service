package com.org.microservice.payment.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

@Builder
@Validated
public record Customer(

        @NotNull(message="id is required")
        @NotBlank(message="id is required")
        String id,

        @NotNull(message="firstName is required")
        @NotBlank(message="firstName is required")
        String firstName,

        @NotNull(message="lastName is required")
        @NotBlank(message="lastName is required")
        String lastName,

        @NotNull(message="email is required")
        @Email(message="email is not valid")
        String email

) {
}
