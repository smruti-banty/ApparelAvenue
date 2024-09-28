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

import com.ApparelAvenue.backend.dto.LoginDto;
import com.ApparelAvenue.backend.model.Customer;
import com.ApparelAvenue.backend.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Tag(name = "Customer Controller", description = "Manage customer-related operations")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/addCustomer")
    @Operation(summary = "Add a new customer", description = "Creates a new customer")
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer customer1 = customerService.addCustomer(customer);
        return new ResponseEntity<>(customer1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a customer by ID", description = "Get a customer by ID")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    public ResponseEntity<Customer> findById(@PathVariable String id) {
        Customer admin = customerService.findById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    @PostMapping("/addAdmin")
    @Operation(summary = "Add a new admin", description = "Create a new admin")
    @ApiResponse(responseCode = "201", description = "Admin created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    public ResponseEntity<Customer> addAdmin(@RequestBody Customer admin) {
        Customer adminCreated = customerService.addAdmin(admin);
        return new ResponseEntity<>(adminCreated, HttpStatus.CREATED);
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Find a customer by email", description = "Get a customer by email")
    @ApiResponse(responseCode = "200", description = "Customer found")
    @ApiResponse(responseCode = "404", description = "Customer not found")
    public ResponseEntity<Customer> getByEmail(@PathVariable String email) {
        Customer customer = customerService.findByEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/loginCustomer/login")
    public ResponseEntity<Customer> authenticateCustomer(@RequestBody LoginDto loginDto) {
        Customer authenticatedCustomer = customerService.authenticateCustomer(loginDto);
        return new ResponseEntity<>(authenticatedCustomer, HttpStatus.OK);
    }
}
