package com.ApparelAvenue.backend.model;

import com.ApparelAvenue.backend.constant.CustomerRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String customerId;
    private String customerName;
    private String customerPassword;
    private String customerEmail;
    private String customerPhoneNumber;
    private String customerAddress;
    @Enumerated(value = EnumType.STRING)
    private CustomerRole customerRole;
}