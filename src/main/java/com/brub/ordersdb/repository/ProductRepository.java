package com.brub.ordersdb.repository;


import com.brub.ordersdb.modelo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
