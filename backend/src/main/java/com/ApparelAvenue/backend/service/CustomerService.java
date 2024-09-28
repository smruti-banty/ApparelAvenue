package com.ApparelAvenue.backend.service;

import com.ApparelAvenue.backend.dto.LoginDto;
import com.ApparelAvenue.backend.model.Customer;

public interface CustomerService {
    Customer addCustomer(Customer customer);

    Customer findById(String id);

    Customer addAdmin(Customer admin);

    Customer findByEmail(String email);

    Customer authenticateCustomer(LoginDto loginDto);
}