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
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String id, Product newProduct) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public void deleteAllProduct() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllProduct'");
    }

    @Override
    public Product deleteProductById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProductById'");
    }

    @Override
    public Product increaseProductQuantity(String id, int quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'increaseProductQuantity'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProductPrice'");
    }

    @Override
    public List<Product> getProducts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProducts'");
    }

    @Override
    public Product getProductById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductById'");
    }
}
