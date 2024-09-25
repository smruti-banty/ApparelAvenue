package com.ApparelAvenue.backend.dto;

import com.ApparelAvenue.backend.constant.ProductStatus;
import com.ApparelAvenue.backend.constant.Section;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductUpdateRequestDto {
        @NotNull(message = "Product name is required")
        private String productName;

        @NotNull(message = "Product description is required")
        private String productDescription;

        @NotNull(message = "Product image URL is required")
        private String productImage;

        @Min(value = 1, message = "Product quantity must be at least 1")
        private int productQuantity;

        @Positive(message = "Product MRP must be a positive number")
        private double productMrp;

        @Positive(message = "Product selling price must be a positive number")
        private double productSellingPrice;

        @NotNull(message = "Product section is required")
        private Section section;

        @NotNull(message = "Product status is required")
        private ProductStatus productStatus;
}
