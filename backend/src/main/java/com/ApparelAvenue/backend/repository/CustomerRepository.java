package com.ApparelAvenue.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ApparelAvenue.backend.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
<<<<<<< HEAD
    Optional<Customer> findByEmail(String email);

    boolean existsByEmail(String email);
}
=======
}
>>>>>>> bc7acb29f1de453be2a85936839c72ef36882cb3
