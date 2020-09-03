package com.brub.ordersdb.repository;

import com.brub.ordersdb.model.OrderStatus;
import com.brub.ordersdb.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByStatus(OrderStatus orderStatus);
    Optional<Orders> findByIdAndUserId(Long id, Long userId);
    Page<Orders> findByStatusNotAndUserId(OrderStatus orderStatus, Long userId, Pageable paginacao);
}