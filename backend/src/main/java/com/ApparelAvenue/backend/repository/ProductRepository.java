package com.ApparelAvenue.backend.repository;

import com.ApparelAvenue.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.productSellingPrice = :price WHERE p.productId = :id")
    void updateProductPrice(@Param("id") String id, @Param("price") double price);
}