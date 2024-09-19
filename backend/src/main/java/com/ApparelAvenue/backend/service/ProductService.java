package com.ApparelAvenue.backend.service;

import java.util.List;

import com.ApparelAvenue.backend.model.Product;
import com.ApparelAvenue.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public interface ProductService {


    Product createProduct();

    Product updateProduct(String id, Product newProduct);

    void deleteAllProduct();

    Product deleteProductById(String id);

    Product increaseProductQuantity(String id, int quantity);

    Product decreaseProductQuantity(String id, int quantity);

    Product updateProductPrice(String id, double price);

    List<Product> getProducts();

    Product getProductById(String id);
}
