package com.hardtech.productservice.repository;

import com.hardtech.productservice.entity.Category;
import com.hardtech.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameLike(String name);

    List<Product> findByCategory(Category category);

    List<Product> findByPriceGreaterThanEqual(BigDecimal price);

    List<Product> findByPriceLessThanEqual(BigDecimal price);


}