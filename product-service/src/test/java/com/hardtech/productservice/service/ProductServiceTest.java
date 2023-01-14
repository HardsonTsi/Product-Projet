package com.hardtech.productservice.service;

import com.hardtech.productservice.entity.Category;
import com.hardtech.productservice.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    List<Product> products = new ArrayList<>();
    @Autowired
    private ProductService productService;

    @Test
    void contextLoads() {
    }

    @Test
    void saveProduct() {
        List.of("Iphone 13", "Samsung", "Mercedes", "PC")
                .forEach(productname -> {
                    Product p = Product.builder()
                            .name("Voiture")
                            .price(BigDecimal.valueOf(5000)).category(Category.MENAGER)
                            .description("Simple comme tous").build();
                    products.add(p);
                });
        products.forEach(product -> assertEquals(Category.MENAGER, productService.saveProduct(product).getCategory()));
    }

    @Test
    void getAllProducts() {
        assertNotNull(productService.getAllProducts());
    }

    @Test
    void getProductById() {
        Product p = productService.getProductById(2L);
        assertNull(p);
    }

    @Test
    void deleteAllProduct() {
        productService.deleteAllProducts();
        assertEquals(0, productService.getAllProducts().size());
    }

    @Test
    void deleteProductById() {
        assertEquals("Product " + 50L + " deleted", productService.deleteProductById(50L));
        assertEquals("Product " + 55L + " not found", productService.deleteProductById(55L));
    }

    @Test
    void getByNameLike() {
        assertNotNull(productService.getByNameLike("V"));
    }

    @Test
    void getByCategory() {
        assertEquals(0, productService.getByCategory(Category.CUISINE).size());
        assertNotNull(productService.getByCategory(Category.MENAGER));
    }

    @Test
    void getByPriceGreaterThanEqual() {
        assertEquals(3, productService.getByPriceGreaterThanEqual(BigDecimal.valueOf(100)).size());
    }

    @Test
    void getByPriceLessThanEqual() {
        assertEquals(0, productService.getByPriceLessThanEqual(BigDecimal.valueOf(100)).size());
    }

}