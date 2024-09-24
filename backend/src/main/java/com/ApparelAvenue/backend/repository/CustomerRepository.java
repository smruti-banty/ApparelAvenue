package com.ApparelAvenue.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ApparelAvenue.backend.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("SELECT c FROM Customer c WHERE c.customerEmail = :email")
    Customer findByEmail(@Param("email") String email);

    @Query("SELECT COUNT(c) > 0 FROM Customer c WHERE c.customerEmail = :email")
    boolean existsByEmail(@Param("email") String email);
}