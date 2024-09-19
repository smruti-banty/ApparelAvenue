package com.ApparelAvenue.backend.dto;

import com.ApparelAvenue.backend.constant.Section;

public class ProductRequestDto {
    private String productName;
    private String productDescription;
    private String productImage;
    private int productQuantity;
    private double productMrp;
    private double productSellingPrice;
    private Section section;
}