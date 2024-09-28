package com.ApparelAvenue.backend.dto;

import com.ApparelAvenue.backend.constant.CustomerRole;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CustomerRequestDto {
    private String customerName;
    private String customerPassword;
    private String customerEmail;
    private String customerPhoneNumber;
    private String customerAddress;
    @Enumerated(value = EnumType.STRING)
    private CustomerRole customerRole;
}
