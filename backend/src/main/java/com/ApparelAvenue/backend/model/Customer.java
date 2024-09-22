package com.ApparelAvenue.backend.model;

import jakarta.persistence.Entity;
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
    private int customerPhoneNumber;
    private String customerAddress;

}
