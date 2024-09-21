package com.ApparelAvenue.backend.controller;

import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PatchMapping("/{id}/increase-quantity/{quantity}")
    public ResponseEntity<Product> increaseProductQuantity(@PathVariable String id, @PathVariable int quantity) {
        Product product = productService.increaseProductQuantity(id, quantity);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}