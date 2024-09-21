package com.ApparelAvenue.backend.dto;

import com.ApparelAvenue.backend.constant.Section;
import lombok.Data;

@Data
public class ProductUpdateRequestDto {
        private String productName;
        private String productDescription;
        private int productQuantity;
        private double productMrp;
        private double productSellingPrice;
        private Section section;
}
