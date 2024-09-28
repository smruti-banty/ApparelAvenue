package com.ApparelAvenue.backend.service.impl;

import org.springframework.stereotype.Service;

import com.ApparelAvenue.backend.constant.CustomerRole;
import com.ApparelAvenue.backend.model.Customer;
import com.ApparelAvenue.backend.repository.CustomerRepository;
import com.ApparelAvenue.backend.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        if (customerRepository.existsByCustomerEmail(customer.getCustomerEmail())) {
            throw new RuntimeException("Email already exists");
        }

        customer.setCustomerRole(CustomerRole.CUSTOMER);
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(String id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    public Customer addAdmin(Customer admin) {
        if (customerRepository.existsByCustomerEmail(admin.getCustomerEmail())) {
            throw new RuntimeException("Email already exists");
        }

        admin.setCustomerRole(CustomerRole.ADMIN);
        return customerRepository.save(admin);
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByCustomerEmail(email).orElseThrow();
    }

    @Override
    public Customer authenticateCustomer(String email, String password) {
        Customer customer = findByEmail(email);
        if (!customer.getCustomerPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        Customer authenticatedCustomer = new Customer();
        authenticatedCustomer.setCustomerName(customer.getCustomerName());
        authenticatedCustomer.setCustomerPhoneNumber(customer.getCustomerPhoneNumber());
        authenticatedCustomer.setCustomerAddress(customer.getCustomerAddress());

        return authenticatedCustomer;
    }
}