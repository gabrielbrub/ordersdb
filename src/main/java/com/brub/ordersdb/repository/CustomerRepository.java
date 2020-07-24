package com.brub.ordersdb.repository;

import com.brub.ordersdb.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);
    Customer findByCpf(String cpf);
}
