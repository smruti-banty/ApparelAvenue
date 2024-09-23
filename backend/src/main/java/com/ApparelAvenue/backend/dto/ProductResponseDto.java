package com.ApparelAvenue.backend.dto;

import com.ApparelAvenue.backend.constant.ProductStatus;
import com.ApparelAvenue.backend.constant.Section;
import lombok.Data;

@Data
public class ProductResponseDto {
    private int productId;
    private String productName;
    private String productDescription; 
    private String productImage;
    private int productQuantity; 
    private double productMrp;
    private double productSellingPrice;
    private Section section;
    private ProductStatus productStatus;
}