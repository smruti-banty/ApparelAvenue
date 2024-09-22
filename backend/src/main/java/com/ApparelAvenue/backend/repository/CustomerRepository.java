package com.ApparelAvenue.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ApparelAvenue.backend.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
