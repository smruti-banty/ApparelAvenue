package com.ApparelAvenue.backend.controller;

import java.util.List;

import com.ApparelAvenue.backend.dto.ProductResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ApparelAvenue.backend.dto.ProductRequestDto;
import com.ApparelAvenue.backend.dto.ProductUpdateRequestDto;
import com.ApparelAvenue.backend.mapper.ProductMapper;
import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name="Product Controller", description = "manage products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    @Operation(summary = "Get a product by ID", description = "ID of the product to be retrieved")
    @ApiResponse(responseCode = "200", description = "product found")
    @ApiResponse(responseCode = "404", description = "product not found")
    public ResponseEntity<?> getProductById(@PathVariable String productId) {
        Product product = productService.getProductById(productId);
        ProductResponseDto productResponseDto = ProductMapper.convertToProductResponseDto(product);
        return ResponseEntity.ok(productResponseDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a product by ID", description = "ID of the product to be deleted")
    @ApiResponse(responseCode = "204", description = "product deleted")
    @ApiResponse(responseCode = "404", description = "product not founud")
    public ResponseEntity<?> deleteProductById(@PathVariable String id) {
        Product product = productService.deleteProductById(id);
        ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(product);
        return new ResponseEntity<>(responseDto, HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/markActive/{id}")
    @Operation(summary = "Mark a product as active", description = "ID of the product is active")
    @ApiResponse(responseCode = "200", description = "product marked as active")
    @ApiResponse(responseCode = "404", description = "product not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public ResponseEntity<ProductResponseDto> activateProductById(@PathVariable String id) {
        Product product = productService.activateProductById(id);
        ProductResponseDto productResponseDto = ProductMapper.convertToProductResponseDto(product);
        return ResponseEntity.ok(productResponseDto);
    }

    @PostMapping
    @Operation(summary = "Create a new product", description = "Create a new product")
    @ApiResponse(responseCode = "201", description = "Product created")
    @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public ResponseEntity<ProductResponseDto> save(@Valid @RequestBody ProductRequestDto dto) {
        Product product = ProductMapper.convertToProduct(dto);
        Product savedProduct = productService.createProduct(product);
        ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(savedProduct);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Update a product", description = "ID of the product is updated")
    @ApiResponse(responseCode = "200", description = "product updated")
    @ApiResponse(responseCode = "404", description = "product not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable String productId,
            @Valid @RequestBody ProductUpdateRequestDto dto) {
        Product newProduct = ProductMapper.convertProductUpdateRequestDtoToProduct(dto);
        Product updatedProduct = productService.updateProduct(productId, newProduct);
        ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(updatedProduct);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}/updatePrice/{price}")
    @Operation(summary = "Update a product's price", description = "Price of the product is updated")
    @ApiResponse(responseCode = "200", description = "product price updated")
    @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public ResponseEntity<?> updateProductPrice(@PathVariable String id, @PathVariable double price) {
        Product updatedProduct = productService.updateProductPrice(id, price);
        ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(updatedProduct);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all products", description = "Delete all products")
    @ApiResponse(responseCode = "204", description = "All products deleted")
    @ApiResponse(responseCode = "400", description = "Internal server error", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public ResponseEntity<Void> deleteAllProduct() {
        productService.deleteAllProduct();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/decrement/{quantity}")
    @Operation(summary = "Decrement a product's quantity", description = "Quantity of the product decrement")
    @ApiResponse(responseCode = "200", description = "Product quantity decremented")
    @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "400", description = "Invalid quantity", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public ResponseEntity<?> decreaseProductQuantity(@PathVariable String id, @PathVariable int quantity) {
        Product product = productService.decreaseProductQuantity(id, quantity);
        ProductResponseDto responseDto = ProductMapper.convertToProductResponseDto(product);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{id}/increase-quantity/{quantity}")
    @Operation(summary = "Increase a product's quantity", description = "Increase a product's quantity")
    @ApiResponse(responseCode = "200", description = "Product quantity increased")
    @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    @ApiResponse(responseCode = "400", description = "Invalid quantity", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
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
    @Operation(summary = "Get all products", description = "Retrieve a list of all products")
    @ApiResponse(responseCode = "200", description = "List of products")
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/active-product")
    @ApiResponse(responseCode = "200", description = "List of active products")
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public ResponseEntity<List<ProductResponseDto>> getActiveProducts() {
        List<Product> activeProducts = productService.getActiveProducts();
        List<ProductResponseDto> responseDtos = activeProducts.stream()
                .map(ProductMapper::convertToProductResponseDto)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/inactive-product")
    @Operation(summary = "Get inactive products", description = "Retrieve a list of inactive products")
    @ApiResponse(responseCode = "200", description = "List of inactive products")
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    public ResponseEntity<List<ProductResponseDto>> getInactiveProducts() {
        List<Product> inactiveProducts = productService.getInactiveProducts();
        List<ProductResponseDto> responseDtos = inactiveProducts.stream()
                .map(ProductMapper::convertToProductResponseDto)
                .toList();
        return ResponseEntity.ok(responseDtos);
    }
}