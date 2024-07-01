package com.org.microservice.product.service;

import com.org.microservice.product.dto.ProductPurchaseRequest;
import com.org.microservice.product.dto.ProductPurchaseResponse;
import com.org.microservice.product.dto.ProductRequest;
import com.org.microservice.product.dto.ProductResponse;
import com.org.microservice.product.exception.ProductPurchaseException;
import com.org.microservice.product.model.Product;
import com.org.microservice.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer addProduct(ProductRequest productRequest) {
        var product = productMapper.mapToProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToReponse)
                .toList();

    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        List<Integer> requestIds = request
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        List<Product> products = productRepository.findAllByIdInOrderById(requestIds);

        if(products.size()!=requestIds.size()){
            throw new ProductPurchaseException("One or more product ids are not found.");
        }

        List<ProductPurchaseResponse> responses = new ArrayList<>();

        for(int i=0;i<products.size();i++){
            Product storedProduct = products.get(i);
            ProductPurchaseRequest requestProduct = request.get(i);

            if(storedProduct.getAvailableQuantity()<requestProduct.quantity()){
                throw new ProductPurchaseException("Required quantity exceeds available quantity for id = "+storedProduct.getId());
            }

            storedProduct.setAvailableQuantity(storedProduct.getAvailableQuantity()-requestProduct.quantity());
            productRepository.save(storedProduct);
            ProductPurchaseResponse productPurchaseResponse = productMapper.mapToProductResponse(storedProduct, requestProduct.quantity());
            responses.add(productPurchaseResponse);
        }

        return responses;
    }

    public ProductResponse findById(Integer productId) {
        var response = productMapper.mapToReponse(productRepository.findById(productId).isEmpty() ?null:productRepository.findById(productId).get());
        if(response==null){
            return null;
        }
        return response;
    }
}
