package com.ApparelAvenue.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApparelAvenue.backend.dto.ProductRequestDto;
import com.ApparelAvenue.backend.dto.ProductUpdateRequestDto;
import com.ApparelAvenue.backend.mapper.ProductMapper;
import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody ProductRequestDto dto) {
        Product product = ProductMapper.convertToProduct(dto);
        Product savedProduct = productService.createProduct(product);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productId, @RequestBody ProductUpdateRequestDto dto) {
        try {
            var newProduct = ProductMapper.convertProductUpdateRequestDtoToProduct(dto);
            var updateProduct = productService.updateProduct(productId, newProduct);
            return ResponseEntity.ok(updateProduct);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}/updatePrice/{price}")
    public ResponseEntity<?> updateProductPrice(@PathVariable String id, @PathVariable double price) {
        try {
            Product updateProduct = productService.updateProductPrice(id, price);
            return new ResponseEntity<Product>(updateProduct, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid input: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
      
    }

    @PatchMapping("/{id}/decrement/{quantity}")
    public ResponseEntity<?> getDecResponseEntity(@PathVariable String id, @PathVariable int quantity) {
        try {
            Product product = productService.decreaseProductQuantity(id, quantity);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}