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
