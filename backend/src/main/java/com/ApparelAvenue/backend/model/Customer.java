package com.ApparelAvenue.backend.model;

import com.ApparelAvenue.backend.constant.CustomerRole;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @NotNull(message = "give customer id")
    private String customerId;

    @NotNull(message = "Please enter customer name")
    private String customerName;

    @NotNull(message = "Please enter the password")
    private String customerPassword;

    @NotNull(message = "Please enter customer Email")
    private String customerEmail;

    @NotNull(message = "Please enter customer Phone number")
    private int customerPhoneNumber;

    @NotNull(message = "Please enter the address of customer")
    private String customerAddress;

    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "Please mention the role ADMIN or CUSTOMER")
    private CustomerRole customerRole;

}