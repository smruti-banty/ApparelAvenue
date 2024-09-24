package com.ApparelAvenue.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ApparelAvenue.backend.constant.ProductStatus;
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
        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            product.setProductStatus(ProductStatus.INACTIVE);
        }

        productRepository.saveAll(products);
    }

    @Override
    public Product deleteProductById(String id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException(id + "does not exist.");
        }

        var product = productRepository.findById(id).orElseThrow();
        product.setProductStatus(ProductStatus.INACTIVE);
        return productRepository.save(product);
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

    @Override
    public Product decreaseProductQuantity(String id, int quantity) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product ID: " + id + " does not exist.");
        }
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        if (product.getProductQuantity() == 0) {
            throw new IllegalArgumentException("Product quantity is already 0. Cannot decrease further.");
        } else if (product.getProductQuantity() < quantity) {
            throw new IllegalArgumentException(
                    "Cannot decrease by " + quantity + ". Available quantity is " + product.getProductQuantity() + ".");
        }
        product.setProductQuantity(product.getProductQuantity() - quantity);
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProductPrice(String id, double price) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product with ID: " + id + " does not exist"));
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero.");
        } else if (price > product.getProductMrp()) {
            throw new IllegalArgumentException("Selling price cannot be lower than the actual price.");
        }
        productRepository.updateProductPrice(id, price);
        return product;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.findById(id).orElseThrow();
    }
}