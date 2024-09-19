package com.ApparelAvenue.backend.service.impl;

import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.repository.ProductRepository;
import com.ApparelAvenue.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Product createProduct() {
        return null;
    }

    @Override
    public Product updateProduct(String id, Product newProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()){
            newProduct.setProductId(id);
            newProduct.setProductImage(optionalProduct.get().getProductImage());
            return productRepository.save(newProduct);
        }else {
            throw new RuntimeException("Product not found");
        }
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
        return null;
    }

    @Override
    public Product updateProductPrice(String id, double price) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProductById(String id) {
        return null;
    }
}
