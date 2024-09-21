package com.ApparelAvenue.backend.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.repository.ProductRepository;
import com.ApparelAvenue.backend.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductRepository productRepository;

    @Override
    public Product createProduct() {
        return null;
    }

    @Override
    public Product updateProduct(String id, Product newProduct) {
        return null;
    }

    @Override
    public void deleteAllProduct() {

    }

    @Override
    public Product deleteProductById(String id) {
        return null;
    }

    @Override
    public Product increaseProductQuantity(String id, int quantity) {
        return null;
    }

    @Override
    public Product decreaseProductQuantity(String id, int quantity) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product ID: " + id + " does not exist.");
        }
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        if (product.getProductQuantity() == 0) {
            throw new IllegalArgumentException("Product quantity is already 0. Cannot decrease further.");
        } else if (product.getProductQuantity() < quantity) {
            throw new IllegalArgumentException("Cannot decrease by " + quantity + ". Available quantity is " + product.getProductQuantity() + ".");
        }
        product.setProductQuantity(product.getProductQuantity() - quantity);
        productRepository.save(product);
        return product;
    }


    @Override
    public Product updateProductPrice(String id, double price) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product getProductById(String id) {
        return null;
    }
}