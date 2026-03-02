package com.customer.service;

import com.customer.entity.Customer;
import com.customer.exception.CustomerNotFoundException;
import com.customer.exception.DuplicateCustomerException;
import com.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public Customer createCustomer(Customer customer) {

        if (repository.existsByEmail(customer.getEmail())) {
            throw new DuplicateCustomerException("Customer with this email already exists.");
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("SYSTEM");

        return repository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {

        Customer existing = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));

        existing.setName(customer.getName());
        existing.setAddress(customer.getAddress());
        existing.setPhone(customer.getPhone());
        existing.setUpdatedAt(LocalDateTime.now());
        existing.setUpdatedBy("SYSTEM");

        return repository.save(existing);
    }

    @Override
    public void deleteCustomer(Long id) {

        Customer existing = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));

        repository.delete(existing);
    }

    @Override
    public Customer getCustomerById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }
}