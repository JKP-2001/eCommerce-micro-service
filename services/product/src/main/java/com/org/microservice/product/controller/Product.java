package com.org.microservice.product.controller;

import com.org.microservice.product.dto.ProductPurchaseRequest;
import com.org.microservice.product.dto.ProductPurchaseResponse;
import com.org.microservice.product.dto.ProductRequest;
import com.org.microservice.product.dto.ProductResponse;
import com.org.microservice.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class Product {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.ok(productService.addProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> request
    ) {
        return ResponseEntity.ok(productService.purchaseProducts(request));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Integer productId
    ) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
}
