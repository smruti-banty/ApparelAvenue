package com.ApparelAvenue.backend.service;

import org.springframework.stereotype.Service;

import com.ApparelAvenue.backend.model.OrderAndCart;

import java.util.List;

@Service
public interface OrderAndCartService {
    public List<OrderAndCart> getAll();

    public List<OrderAndCart> getOrders();

    public List<OrderAndCart> getCarts();

    public List<OrderAndCart> getCartsById(String cutomerId);

    public List<OrderAndCart> getOrdersById(String customerId);
}
