package com.org.microservice.order.service.product;

import com.org.microservice.order.dto.product.ProductPurchaseResponse;
import com.org.microservice.order.dto.product.PurchaseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name="product-service",
        url="${application.config.product-url}"
)
public interface ProductService {
    @PostMapping("/purchase")
    Optional<List<ProductPurchaseResponse>> purchaseProducts(@RequestHeader(value = "Authorization", required = true) String token, @RequestBody List<PurchaseRequest> productPurchaseRequest);
}
