package com.ApparelAvenue.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.ApparelAvenue.backend.dto.OrderAndCartRequestDto;
import com.ApparelAvenue.backend.dto.OrderAndCartResponseDto;
import com.ApparelAvenue.backend.mapper.OrderAndCartMapper;
import com.ApparelAvenue.backend.service.OrderAndCartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order-and-cart")
@RequiredArgsConstructor
public class OrderAndCartController {
    private final OrderAndCartService orderAndCartService;

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

    @GetMapping("/orders/{customerId}")
    public ResponseEntity<List<OrderAndCartResponseDto>> getAllOrders(@PathVariable String customerId) {
        var orderAndCarts = orderAndCartService.getOrdersById(customerId);
        return ResponseEntity
                .ok(OrderAndCartMapper.convertToListOfOrderAndCartResponseDto(orderAndCarts));
    }

    @GetMapping("/carts")
    public ResponseEntity<List<OrderAndCartResponseDto>> getAllCarts() {
        return ResponseEntity
                .ok(OrderAndCartMapper.convertToListOfOrderAndCartResponseDto(orderAndCartService.getCarts()));
    }

    @GetMapping("/carts/{customerId}")
    public ResponseEntity<List<OrderAndCartResponseDto>> getAllCarts(@PathVariable String customerId) {
        var orderAndCarts = orderAndCartService.getCartsById(customerId);
        return ResponseEntity
                .ok(OrderAndCartMapper.convertToListOfOrderAndCartResponseDto(orderAndCarts));
    }

    @PostMapping("/save")
    public ResponseEntity<OrderAndCartResponseDto> saveCart(
            @RequestBody OrderAndCartRequestDto orderAndCartRequestDto) {
        var orderAndCart = OrderAndCartMapper.convertToOrderAndCart(orderAndCartRequestDto);

        return new ResponseEntity<>(OrderAndCartMapper.convertToOrderAndCartResponseDto(
                orderAndCartService.addOrderAndCart(orderAndCart, orderAndCartRequestDto.getCustomerId(),
                        orderAndCartRequestDto.getProductIds())),
                HttpStatus.CREATED);
    }

    @PostMapping("/order-all/{customerId}")
    public ResponseEntity<List<OrderAndCartResponseDto>> orderAll(@PathVariable String customerId) {
        return new ResponseEntity<>(
                OrderAndCartMapper.convertToListOfOrderAndCartResponseDto(orderAndCartService.moveToOrder(customerId)),
                HttpStatus.CREATED);
    }
}
