package com.brub.ordersdb.repository;


import com.brub.ordersdb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    List<Product> findByUserId(Long userId);
    Optional<Product> findByIdAndUserId(Long id, Long userId);
}
