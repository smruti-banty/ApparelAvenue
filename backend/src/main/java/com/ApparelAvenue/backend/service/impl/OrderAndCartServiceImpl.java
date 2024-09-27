package com.ApparelAvenue.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ApparelAvenue.backend.constant.OrderAndCartStatus;
import com.ApparelAvenue.backend.model.OrderAndCart;
import com.ApparelAvenue.backend.repository.OrderAndCartRepository;
import com.ApparelAvenue.backend.service.OrderAndCartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderAndCartServiceImpl implements OrderAndCartService {
    private final OrderAndCartRepository orderAndCartRepository;

    @Override
    public List<OrderAndCart> getAll() {
        return orderAndCartRepository.findAll();
    }

    @Override
    public List<OrderAndCart> getOrders() {
        return orderAndCartRepository.findByOrderAndCartStatus(OrderAndCartStatus.ORDER);
    }

    @Override
    public List<OrderAndCart> getCarts() {
        return orderAndCartRepository.findByOrderAndCartStatus(OrderAndCartStatus.CART);
    }

    @Override
    public List<OrderAndCart> getCartsById(String cutomerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCartsById'");
    }

    @Override
    public List<OrderAndCart> getOrdersById(String customerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrdersById'");
    }

}
