package com.org.microservice.customer.repository;

import com.org.microservice.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    public Customer findByEmail(String email);
}
