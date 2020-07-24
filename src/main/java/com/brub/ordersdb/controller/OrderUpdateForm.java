package com.brub.ordersdb.controller;

import com.brub.ordersdb.model.Item;
import com.brub.ordersdb.model.OrderStatus;
import com.brub.ordersdb.model.Orders;
import com.brub.ordersdb.repository.CustomerRepository;
import com.brub.ordersdb.repository.OrderRepository;
import com.brub.ordersdb.repository.ProductRepository;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderUpdateForm {
    @NotNull
    private Map<String, Integer> products;
    @NotNull
    private String date;
    @NotNull
    private String customerCpf;
    @NotNull
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Orders update(Long id, OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        Orders order = orderRepository.getOne(id);
        return getOrder(productRepository, customerRepository, order);
    }


    private Orders getOrder(ProductRepository productRepository, CustomerRepository customerRepository, Orders order) {
        List<Item> itemList = new ArrayList<>();
        products.forEach((k, v) -> {
            Item item = new Item();
            item.setProduct(productRepository.findByName(k));
            item.setAmount(v);
            item.setOrder(order);
            itemList.add(item);
        });
        order.setItems(itemList);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        order.setOrderDate(dateTime);
        status = status.toUpperCase();
        order.setStatus(OrderStatus.valueOf(status));
        order.setCustomer(customerRepository.findByCpf(customerCpf));
        return order;
    }


    public String getCustomerCpf() {
        return customerCpf;
    }

    public void setCustomerCpf(String customerCpf) {
        this.customerCpf = customerCpf;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
