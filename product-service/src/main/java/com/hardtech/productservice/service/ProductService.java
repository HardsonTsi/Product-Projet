package com.hardtech.productservice.service;

import com.hardtech.productservice.entity.Category;
import com.hardtech.productservice.entity.Product;
import com.hardtech.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product p) {
        return productRepository.save(p);
    }

    public Product updateProduct(Product p) {
        return productRepository.save(p);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public String deleteProductById(Long id) {
        Product product = getProductById(id);
        String message = "Product " + id + " not found";
        if (Objects.nonNull(product)) {
            productRepository.deleteById(id);
            message = "Product " + id + " deleted";
        }
        return message;
    }

    public String deleteAllProducts() {
        productRepository.deleteAll();
        return "All products are deleted";
    }


    public List<Product> getByNameLike(String name) {
        return productRepository.findByNameLike("%" + name + "%");
    }

    public List<Product> getByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getByPriceGreaterThanEqual(BigDecimal price) {
        return productRepository.findByPriceGreaterThanEqual(price);
    }

    public List<Product> getByPriceLessThanEqual(BigDecimal price) {
        return productRepository.findByPriceLessThanEqual(price);
    }

}
