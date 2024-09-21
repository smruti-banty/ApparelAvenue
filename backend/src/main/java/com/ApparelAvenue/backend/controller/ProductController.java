package com.ApparelAvenue.backend.controller;

import com.ApparelAvenue.backend.dto.ProductRequestDto;
import com.ApparelAvenue.backend.dto.ProductUpdateRequestDto;
import com.ApparelAvenue.backend.mapper.ProductMapper;
import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable String productId) {
        try {
            Product product = productService.getProductById(productId);  // Declare product only once
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with id: " + productId);  // Use productId instead of id
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the product.");
        }
    }

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

    @DeleteMapping("/all")
    public void deleteAllProduct() {
        productService.deleteAllProduct();
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
