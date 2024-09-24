package com.ApparelAvenue.backend.dto;

import java.util.List;

import com.ApparelAvenue.backend.constant.OrderAndCartStatus;
import com.ApparelAvenue.backend.model.Customer;

import lombok.Data;

@Data
public class OrderAndCartResponseDto {
    private int orderId;
    private Customer customer;
    List<ProductResponseDto> productResponseDtos;
    OrderAndCartStatus orderAndCartStatus;
}
