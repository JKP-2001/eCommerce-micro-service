package com.org.microservice.product.exception;

import com.org.microservice.product.dto.ProductPurchaseResponse;

import java.util.List;

public class ProductPurchaseException extends RuntimeException {
    public ProductPurchaseException(String s) {
        super(s);
    }
}
