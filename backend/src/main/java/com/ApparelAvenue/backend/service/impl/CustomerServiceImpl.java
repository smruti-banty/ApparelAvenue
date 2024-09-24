package com.ApparelAvenue.backend.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(String id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer addAdmin(Customer admin) {
        admin.setCustomerRole(CustomerRole.ADMIN);
        return customerRepository.save(admin);
    }

    public Customer findByEmail(String email) {

        if (customerRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists. Cannot log in.");
        }

        Customer customer = customerRepository.findByEmail(email);

        // if (customer == null) {
        // throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email does not
        // exist. Cannot log in.");
        // }

        return customer;
    }

}