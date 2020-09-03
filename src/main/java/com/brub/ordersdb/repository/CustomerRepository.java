package com.brub.ordersdb.repository;

import com.brub.ordersdb.model.Customer;
import com.brub.ordersdb.model.Orders;
import com.brub.ordersdb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByUserId(Long userId);
    Optional<Customer> findByIdAndUserId(Long id, Long userId);
    Customer findByCpf(String cpf);
}
