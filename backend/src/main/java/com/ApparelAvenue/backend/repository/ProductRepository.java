package com.ApparelAvenue.backend.repository;

import com.ApparelAvenue.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
