package com.ApparelAvenue.backend.model;

import com.ApparelAvenue.backend.constant.Section;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    private String productName;
    private String productDescription;
    private String productImage;
    private String productQuantity;
    private String productMrp;
    private String productSellingPrice;
    @Enumerated(value = EnumType.STRING)
    private Section section;
}
