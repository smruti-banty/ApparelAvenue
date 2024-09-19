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

    @Override
    public Product createProduct() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProduct'");
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
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException(id + "does not exist.");
        }
        var product = repository.findById(id).get();
        repository.delete(product);
        return product;
    }

    @Override
    public Product increaseProductQuantity(String id, int quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'increaseProductQuantity'");
    }

    @Override
    public Product decreaseProductQuantity(String id, int quantity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'decreaseProductQuantity'");
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
