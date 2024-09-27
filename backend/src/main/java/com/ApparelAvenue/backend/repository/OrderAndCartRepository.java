package com.ApparelAvenue.backend.repository;

import com.ApparelAvenue.backend.constant.OrderAndCartStatus;
import com.ApparelAvenue.backend.model.OrderAndCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderAndCartRepository extends JpaRepository<OrderAndCart, Integer> {
    @Query("SELECT o FROM OrderAndCart o WHERE o.orderAndCartStatus = :orderStatus")
    public List<OrderAndCart> findByOrderAndCartStatus(@Param("orderStatus") OrderAndCartStatus orderAndCartStatus);
}
