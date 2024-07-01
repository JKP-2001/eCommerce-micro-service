package com.org.microservice.product.exception;

import com.org.microservice.product.dto.ProductPurchaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ProductPurchaseException extends RuntimeException {
    private String msg;
}
