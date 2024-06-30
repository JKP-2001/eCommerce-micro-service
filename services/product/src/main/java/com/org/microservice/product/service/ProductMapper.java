package com.org.microservice.product.service;

import com.org.microservice.product.dto.ProductPurchaseResponse;
import com.org.microservice.product.dto.ProductRequest;
import com.org.microservice.product.dto.ProductResponse;
import com.org.microservice.product.model.Category;
import com.org.microservice.product.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductMapper {
    public Product mapToProduct(ProductRequest productRequest) {
        return
                Product.builder()
                        .id(productRequest.id())
                        .price(productRequest.price())
                        .name(productRequest.name())
                        .availableQuantity(productRequest.availableQuantity())
                        .description(productRequest.description())
                        .category(
                                Category.builder()
                                        .id(productRequest.categoryId())
                                        .build()
                        )
                        .build();
    }

    public ProductResponse mapToReponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }

//    Integer productId,
//    String name,
//    String description,
//    BigDecimal price,
//    double quantity

    public ProductPurchaseResponse mapToProductResponse(Product storedProduct, double quantity) {
        return new ProductPurchaseResponse(
                storedProduct.getId(),
                storedProduct.getName(),
                storedProduct.getDescription(),
                storedProduct.getPrice(),
                quantity
        );
    }
}
