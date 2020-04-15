package com.brub.ordersdb.repository;

import com.brub.ordersdb.modelo.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomerName(String customerName);
}