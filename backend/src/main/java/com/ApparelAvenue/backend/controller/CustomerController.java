package com.ApparelAvenue.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ApparelAvenue.backend.constant.OrderAndCartStatus;
import com.ApparelAvenue.backend.dto.CustomerRequestDto;
import com.ApparelAvenue.backend.model.Customer;
import com.ApparelAvenue.backend.model.OrderAndCart;
import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.repository.CustomerRepository;
import com.ApparelAvenue.backend.repository.OrderAndCartRepository;
import com.ApparelAvenue.backend.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerRepository customerRepository;

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerRequestDto, customer);
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
