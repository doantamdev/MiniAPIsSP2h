package com.SpringBootTutorial.FirstProject.repositories;

import com.SpringBootTutorial.FirstProject.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);
}
