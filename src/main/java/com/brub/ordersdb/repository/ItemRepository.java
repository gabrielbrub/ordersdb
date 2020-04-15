package com.brub.ordersdb.repository;

import com.brub.ordersdb.modelo.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

