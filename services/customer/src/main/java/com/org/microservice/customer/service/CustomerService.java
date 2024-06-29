package com.org.microservice.customer.service;

import com.org.microservice.customer.dto.CustomerRequest;
import com.org.microservice.customer.dto.CustomerResponse;
import com.org.microservice.customer.model.Customer;
import com.org.microservice.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    public String create(CustomerRequest customerRequest) {
        Customer ifCustomer = customerRepository.findByEmail(customerRequest.email());
        if(ifCustomer != null) {
            return "Email Id already exists";
        }
        Customer customer = customerMapper.toCustomer(customerRequest);
        customerRepository.save(customer);
        return "Customer created successfully with id=" +customer.getId();
    }

    public String update(CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(customerRequest.id()).orElse(null);
        if(customer == null) {
            return "Customer not found";
        }
        else{
            Customer newCustomer = customerMapper.toCustomer(customerRequest);
            customerRepository.save(newCustomer);
            return "Customer updated successfully with id=" +newCustomer.getId();
        }
    }

    public List<CustomerResponse> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customerMapper::toCustomerResponse).toList();
    }

    public CustomerResponse findById(String id){
        return customerMapper.toCustomerResponse(Objects.requireNonNull(customerRepository.findById(id).orElse(null)));
    }
}
