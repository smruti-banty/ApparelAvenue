package com.ApparelAvenue.backend.dto;

import lombok.Data;

@Data
public class CustomerResponseDto {
    private String customerId;
    private String customerName;
    private String customerEmail;
    private int customerPhoneNumber;
    private String customerAddress;
}
