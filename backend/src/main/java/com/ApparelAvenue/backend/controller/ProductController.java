package com.ApparelAvenue.backend.controller;

import com.ApparelAvenue.backend.dto.ProductRequestDto;
import com.ApparelAvenue.backend.dto.ProductUpdateRequestDto;
import com.ApparelAvenue.backend.mapper.ProductMapper;
import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequestDto dto){
        try {
            var newProduct = ProductMapper.convertProductUpdateRequestDtoToProduct(dto);
            var updateProduct = productService.updateProduct(productId,newProduct);
            return ResponseEntity.ok(updateProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
