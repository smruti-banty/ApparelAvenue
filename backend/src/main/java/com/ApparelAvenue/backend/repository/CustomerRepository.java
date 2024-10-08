package com.ApparelAvenue.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ApparelAvenue.backend.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findByCustomerEmail(String email);

    boolean existsByCustomerEmail(String email);
}
