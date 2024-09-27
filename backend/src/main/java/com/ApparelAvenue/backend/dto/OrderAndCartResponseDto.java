package com.ApparelAvenue.backend.dto;

import java.util.List;

import com.ApparelAvenue.backend.constant.OrderAndCartStatus;

import lombok.Data;

@Data
public class OrderAndCartResponseDto {
    private int orderId;
    private CustomerResponseDto customerResponseDto;
    List<ProductResponseDto> productResponseDtos;
    OrderAndCartStatus orderAndCartStatus;
}
