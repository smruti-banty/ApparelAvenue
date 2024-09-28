package com.ApparelAvenue.backend.repository;

import com.ApparelAvenue.backend.constant.OrderAndCartStatus;
import com.ApparelAvenue.backend.model.OrderAndCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderAndCartRepository extends JpaRepository<OrderAndCart, Integer> {
    @Query("SELECT o FROM OrderAndCart o WHERE o.orderAndCartStatus = :orderAndCartStatus")
    List<OrderAndCart> findByOrderAndCartStatus(OrderAndCartStatus orderAndCartStatus);

    @Query("SELECT o FROM OrderAndCart o WHERE o.customer.customerId = :customerId AND o.orderAndCartStatus = :orderAndCartStatus")
    List<OrderAndCart> findByCustomerCustomerIdAndOrderAndCartStatus(String customerId,
            OrderAndCartStatus orderAndCartStatus);
}
