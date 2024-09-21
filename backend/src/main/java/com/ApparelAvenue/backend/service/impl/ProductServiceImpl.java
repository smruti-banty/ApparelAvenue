package com.ApparelAvenue.backend.service.impl;

import java.util.List;
import java.util.Optional;

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
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            newProduct.setProductId(id);
            newProduct.setProductImage(optionalProduct.get().getProductImage());
            return productRepository.save(newProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    @Override
    public void deleteAllProduct() {
        productRepository.deleteAll();
    }

    @Override
    public Product deleteProductById(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteProductById'");
    }

    @Override
    public Product increaseProductQuantity(String id, int quantity) {
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
        throw new UnsupportedOperationException("Unimplemented method 'updateProductPrice'");
    }

    @Override
    public List<Product> getProducts() {
        throw new UnsupportedOperationException("Unimplemented method 'getProducts'");
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow();
    }
}
