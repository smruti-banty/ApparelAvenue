package com.ApparelAvenue.backend.model;

import com.ApparelAvenue.backend.constant.ProductStatus;
import com.ApparelAvenue.backend.constant.Section;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;
    private String productName;
    private String productDescription;
    private String productImage;
    private int productQuantity;
    private double productMrp;
    private double productSellingPrice;
    @Enumerated(value = EnumType.STRING)
    private Section section;
    @Enumerated(value = EnumType.STRING)
    private ProductStatus productStatus;

    @ManyToMany(mappedBy = "products")
    private List<OrderAndCart> orderAndCarts;
}
