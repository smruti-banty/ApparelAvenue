package com.ApparelAvenue.backend.mapper;

import com.ApparelAvenue.backend.dto.ProductRequestDto;
import com.ApparelAvenue.backend.dto.ProductUpdateRequestDto;
import com.ApparelAvenue.backend.model.Product;
import org.springframework.beans.BeanUtils;

public final class ProductMapper {
    private ProductMapper() {
    }

    public static Product convertToProduct(ProductRequestDto dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        return product;
    }
    public static Product convertProductUpdateRequestDtoToProduct(ProductUpdateRequestDto dto) {
        Product product = new Product();
        BeanUtils.copyProperties(dto, product);
        return product;
    }
}