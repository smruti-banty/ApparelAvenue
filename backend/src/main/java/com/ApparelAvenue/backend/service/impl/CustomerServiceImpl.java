package com.ApparelAvenue.backend.service.impl;

import org.springframework.stereotype.Service;

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

}
