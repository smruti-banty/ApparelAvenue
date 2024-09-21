package com.ApparelAvenue.backend.service.impl;

import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.repository.ProductRepository;
import com.ApparelAvenue.backend.service.ProductService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
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

        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("product Id" + id + "doesnot Exists");

        }
        Product product = productRepository.findById(id).get();
        product.setProductQuantity(product.getProductQuantity() + quantity);
        productRepository.save(product);
        return product;
    }

    public Product findProductById(String productId) {
        return null;
    }

    @Override
    public Product decreaseProductQuantity(String id, int quantity) {
        return null;
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
