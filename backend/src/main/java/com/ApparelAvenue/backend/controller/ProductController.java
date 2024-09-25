
package com.ApparelAvenue.backend.controller;

import java.util.List;

import com.ApparelAvenue.backend.dto.ProductResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable String productId) {
        try {
            Product product = productService.getProductById(productId);
            ProductResponseDto productResponseDto = ProductMapper.convertToProductResponseDto(product);
            return ResponseEntity.ok(productResponseDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with id: " + productId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the product.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable String id) {
        try {
            Product product = productService.deleteProductById(id);
            ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(product);
            return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@Valid @RequestBody ProductRequestDto dto) {
        Product product = ProductMapper.convertToProduct(dto);
        Product savedProduct = productService.createProduct(product);
        ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(savedProduct);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable String productId,
            @Valid @RequestBody ProductUpdateRequestDto dto) {
        try {
            Product newProduct = ProductMapper.convertProductUpdateRequestDtoToProduct(dto);
            Product updatedProduct = productService.updateProduct(productId, newProduct);
            ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(updatedProduct);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}/updatePrice/{price}")
    public ResponseEntity<?> updateProductPrice(@PathVariable String id, @PathVariable double price) {
        try {
            Product updatedProduct = productService.updateProductPrice(id, price);
            ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(updatedProduct);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid input: " + e.getMessage());
        }
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllProduct() {
        productService.deleteAllProduct();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/decrement/{quantity}")
    public ResponseEntity<?> decreaseProductQuantity(@PathVariable String id, @PathVariable int quantity) {
        try {
            Product product = productService.decreaseProductQuantity(id, quantity);
            ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(product);
            return ResponseEntity.ok(responseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/increase-quantity/{quantity}")
    public ResponseEntity<ProductResponseDto> increaseProductQuantity(@PathVariable String id,
            @PathVariable int quantity) {
        Product product = productService.increaseProductQuantity(id, quantity);
        ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(product);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> getActiveAndInactiveProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/all")
    public List<Product> getProducts() {
        return productService.getProducts();
    }
}