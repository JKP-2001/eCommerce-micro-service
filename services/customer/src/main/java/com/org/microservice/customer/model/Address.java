package com.org.microservice.customer.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
@Validated
public class Address {
    @NotBlank(message = "street is required")
    private String street;
    @Pattern(regexp = "\\d+", message = "House number must be numeric")
    private String houseNumber;

    @Pattern(regexp = "\\d{6}", message = "Zip code must be exactly 6 digits")
    private String zipcode;
}
