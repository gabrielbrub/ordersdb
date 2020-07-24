package com.brub.ordersdb.repository;

import com.brub.ordersdb.model.OrderStatus;
import com.brub.ordersdb.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByCustomerCpf(String customerCpf);
    List<Orders> findByStatus(OrderStatus orderStatus);
    //List<Orders> findByStatusNot(OrderStatus orderStatus);
    //@Query("SELECT id, creationDate, orderDate, customer FROM Orders WHERE status = 'DONE'")
    Page<Orders> findByStatusNot(OrderStatus orderStatus, Pageable paginacao);
}