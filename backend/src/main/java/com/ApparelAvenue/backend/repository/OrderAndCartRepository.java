package com.ApparelAvenue.backend.repository;

import com.ApparelAvenue.backend.model.OrderAndCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAndCartRepository extends JpaRepository<OrderAndCart,Integer> {
}
