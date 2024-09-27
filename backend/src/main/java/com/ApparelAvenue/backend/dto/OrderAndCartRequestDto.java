package com.ApparelAvenue.backend.dto;

import java.util.List;

import com.ApparelAvenue.backend.constant.OrderAndCartStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class OrderAndCartRequestDto {
    private int orderId;
    private String customerId;

    List<String> productIds;

    @Enumerated(EnumType.STRING)
    OrderAndCartStatus orderAndCartStatus;
}
