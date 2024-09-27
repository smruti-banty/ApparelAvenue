package com.ApparelAvenue.backend.controller;

import java.util.List;

import com.ApparelAvenue.backend.dto.ProductResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ApparelAvenue.backend.dto.ProductRequestDto;
import com.ApparelAvenue.backend.dto.ProductUpdateRequestDto;
import com.ApparelAvenue.backend.mapper.ProductMapper;
import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.service.ProductService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable String productId) {
        Product product = productService.getProductById(productId);
        ProductResponseDto productResponseDto = ProductMapper.convertToProductResponseDto(product);
        return ResponseEntity.ok(productResponseDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable String id) {
        Product product = productService.deleteProductById(id);
        ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(product);
        return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/markActive/{id}")
    public ResponseEntity<ProductResponseDto> activateProductById(@PathVariable String id) {
        Product product = productService.activateProductById(id);
        ProductResponseDto productResponseDto = ProductMapper.convertToProductResponseDto(product);
        return ResponseEntity.ok(productResponseDto);
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
        Product newProduct = ProductMapper.convertProductUpdateRequestDtoToProduct(dto);
        Product updatedProduct = productService.updateProduct(productId, newProduct);
        ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(updatedProduct);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}/updatePrice/{price}")
    public ResponseEntity<?> updateProductPrice(@PathVariable String id, @PathVariable double price) {
        Product updatedProduct = productService.updateProductPrice(id, price);
        ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(updatedProduct);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Void> deleteAllProduct() {
        productService.deleteAllProduct();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/decrement/{quantity}")
    public ResponseEntity<?> decreaseProductQuantity(@PathVariable String id, @PathVariable int quantity) {
        Product product = productService.decreaseProductQuantity(id, quantity);
        ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(product);
        return ResponseEntity.ok(responseDto);
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

    @GetMapping("/active-product")
    public ResponseEntity<List<ProductResponseDto>> getActiveProducts() {
        List<Product> activeProducts = productService.getActiveProducts();
        List<ProductResponseDto> responseDtos = activeProducts.stream()
                .map(ProductMapper::convertToProductResponseDto)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/inactive-product")
    public ResponseEntity<List<ProductResponseDto>> getInactiveProducts() {
        List<Product> inactiveProducts = productService.getInactiveProducts();
        List<ProductResponseDto> responseDtos = inactiveProducts.stream()
                .map(ProductMapper::convertToProductResponseDto)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }
}