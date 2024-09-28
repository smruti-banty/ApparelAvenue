package com.ApparelAvenue.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ApparelAvenue.backend.constant.OrderAndCartStatus;
import com.ApparelAvenue.backend.model.OrderAndCart;
import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.repository.OrderAndCartRepository;
import com.ApparelAvenue.backend.service.CustomerService;
import com.ApparelAvenue.backend.service.OrderAndCartService;
import com.ApparelAvenue.backend.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderAndCartServiceImpl implements OrderAndCartService {
    private final OrderAndCartRepository orderAndCartRepository;
    private final CustomerService customerService;
    private final ProductService productService;

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
    public List<OrderAndCart> getCartsById(String customerId) {
        return orderAndCartRepository.findByCustomerCustomerIdAndOrderAndCartStatus(customerId,
                OrderAndCartStatus.CART);
    }

    @Override
    public List<OrderAndCart> getOrdersById(String customerId) {
        return orderAndCartRepository.findByCustomerCustomerIdAndOrderAndCartStatus(customerId,
                OrderAndCartStatus.ORDER);
    }

    @Override
    public OrderAndCart addOrderAndCart(OrderAndCart orderAndCart, String customerId, List<String> productIds) {
        var customer = customerService.findById(customerId);
        orderAndCart.setCustomer(customer);
        List<Product> products = productIds.stream().map(id -> productService.getProductById(id)).toList();
        orderAndCart.setProducts(products);
        return orderAndCartRepository.save(orderAndCart);
    }

    @Override
    public List<OrderAndCart> moveToOrder(String customerId) {
        var carts = getCartsById(customerId);

        List<OrderAndCart> orders = carts.stream().map(cart -> {
            cart.setOrderAndCartStatus(OrderAndCartStatus.ORDER);
            return cart;
        }).toList();
        orderAndCartRepository.saveAll(orders);
        return orders;
    }

}
