package com.ApparelAvenue.backend.repository;

import com.ApparelAvenue.backend.model.Product;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.productSellingPrice = :price WHERE p.productId = :id")
    void updateProductPrice(@Param("id") String id, @Param("price") double price);

    @Query("UPDATE Product product SET product.productQuantity = product.productQuantity - :quantity WHERE product.productId = :id")
    void decrementByQuantity(@Param("id") String id, @Param("quantity") int quantity);

    List<Product> findByActive(boolean active);
}