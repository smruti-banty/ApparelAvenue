package com.ApparelAvenue.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import com.ApparelAvenue.backend.constant.OrderAndCartStatus;
import com.ApparelAvenue.backend.dto.OrderAndCartRequestDto;
import com.ApparelAvenue.backend.dto.OrderAndCartResponseDto;
import com.ApparelAvenue.backend.mapper.OrderAndCartMapper;
import com.ApparelAvenue.backend.model.OrderAndCart;
import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.repository.CustomerRepository;
import com.ApparelAvenue.backend.repository.OrderAndCartRepository;
import com.ApparelAvenue.backend.service.OrderAndCartService;
import com.ApparelAvenue.backend.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order-and-cart")
@RequiredArgsConstructor
public class OrderAndCartController {
    private final OrderAndCartService orderAndCartService;
    private final ProductService productService;
    private final CustomerRepository customerRepository;
    private final OrderAndCartRepository orderAndCartRepository;

    @GetMapping("/orders-and-carts")
    public ResponseEntity<List<OrderAndCartResponseDto>> getAllOrderAndCarts() {
        return ResponseEntity
                .ok(OrderAndCartMapper.convertToListOfOrderAndCartResponseDto(orderAndCartService.getAll()));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderAndCartResponseDto>> getAllOrders() {
        return ResponseEntity
                .ok(OrderAndCartMapper.convertToListOfOrderAndCartResponseDto(orderAndCartService.getOrders()));
    }

    @GetMapping("/carts")
    public ResponseEntity<List<OrderAndCartResponseDto>> getAllCarts() {
        return ResponseEntity
                .ok(OrderAndCartMapper.convertToListOfOrderAndCartResponseDto(orderAndCartService.getCarts()));
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveCart(OrderAndCartRequestDto orderAndCartRequestDto) {
        OrderAndCart orderAndCart = new OrderAndCart();
        orderAndCart.setCustomer(customerRepository.findById("0f9286bd-ddc9-414c-ac21-fbd41be10ea1").get());

        List<Product> products = new ArrayList<>();
        products.add(productService.getProductById("7c7bb1bd-45df-47b3-b60d-b507db4f4057"));
        products.add(productService.getProductById("958ffd51-b1a2-47e4-a6c8-9572f8c54177"));

        orderAndCart.setProducts(products);
        orderAndCart.setOrderAndCartStatus(OrderAndCartStatus.CART);

        orderAndCartRepository.save(orderAndCart);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
