package com.ApparelAvenue.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ApparelAvenue.backend.model.Customer;
import com.ApparelAvenue.backend.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer customer1 = customerService.addCustomer(customer);
        return new ResponseEntity<>(customer1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable String id) {
        Customer admin = customerService.findById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PostMapping("/addAdmin")
    public ResponseEntity<Customer> addAdmin(@RequestBody Customer admin) {
        Customer adminCreated = customerService.addAdmin(admin);
        return new ResponseEntity<>(adminCreated, HttpStatus.CREATED);
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<Customer> getByEmail(@RequestParam String email) {
        Customer customer = customerService.findByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}