package com.ApparelAvenue.backend.service;

import org.springframework.stereotype.Service;

import com.ApparelAvenue.backend.model.OrderAndCart;

import java.util.List;

@Service
public interface OrderAndCartService {
    List<OrderAndCart> getAll();

    List<OrderAndCart> getOrders();

    List<OrderAndCart> getCarts();

    List<OrderAndCart> getCartsById(String customerId);

    List<OrderAndCart> getOrdersById(String customerId);

    OrderAndCart addOrderAndCart(OrderAndCart orderAndCart, String customerId, List<String> productIds);

    List<OrderAndCart> moveToOrder(String customerId);
}
