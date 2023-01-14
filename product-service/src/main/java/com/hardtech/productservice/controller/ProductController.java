package com.hardtech.productservice.controller;

import com.hardtech.productservice.entity.Category;
import com.hardtech.productservice.entity.Product;
import com.hardtech.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete/all")
    public String deleteAllProducts() {
        return productService.deleteAllProducts();
    }

    @PostMapping("/save")
    Product saveProduct(@Valid @RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/update")
    Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping("/{id}")
    Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }

    @GetMapping("/names/{name}")
    List<Product> getByNameLike(@PathVariable String name) {
        return productService.getByNameLike(name);
    }

    @GetMapping("/categories/{category}")
    List<Product> getByCategory(@PathVariable Category category) {
        return productService.getByCategory(category);
    }

    @GetMapping("/prices/greaterthanequal/{price}")
    List<Product> getByPriceGreaterThanEqual(@PathVariable BigDecimal price) {
        return productService.getByPriceGreaterThanEqual(price);
    }

    @GetMapping("/prices/lessthanequal/{price}")
    List<Product> getByPriceLessThanEqual(@PathVariable BigDecimal price) {
        return productService.getByPriceLessThanEqual(price);
    }


}
