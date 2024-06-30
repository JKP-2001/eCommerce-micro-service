package com.org.microservice.product.repository;

import com.org.microservice.product.dto.ProductPurchaseRequest;
import com.org.microservice.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByIdInOrderById(List<Integer> request);
}
