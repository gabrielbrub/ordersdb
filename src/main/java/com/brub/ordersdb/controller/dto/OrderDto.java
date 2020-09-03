package com.brub.ordersdb.controller.dto;

import com.brub.ordersdb.model.Customer;
import com.brub.ordersdb.model.Item;
import com.brub.ordersdb.model.OrderStatus;
import com.brub.ordersdb.model.Orders;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class OrderDto {
    private List<Item> items;
    private Map<String, Integer> products;
    private Long id;
    private String date;
    private OrderStatus status;
    private LocalDateTime creationDate;
    //private Customer customer;
    private Map<String, String> customer;

    public Map<String, Integer> getProducts() {
        return products;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<Item> getItems() {
        return items;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Map<String, String> getCustomer(){
        return customer;
    }

    private void setCustomer(Customer c){
        customer = new HashMap<>();
        customer.put("cpf", c.getCpf());
        customer.put("name", c.getName());
    }


    public OrderDto(Orders o){
        this.id = o.getId();
        this.status = o.getStatus();
        this.items = o.getItems();
        this.products = new HashMap<>();
        o.getItems().stream().forEach((i) -> {
            products.put(i.getProduct().getName(), i.getAmount());
        });
        this.date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(o.getOrderDate());
        this.creationDate = o.getCreationDate();
        this.setCustomer(o.getCustomer());
//        this.customer = o.getCustomer();
    }

    public static List<OrderDto> converter(List<Orders> orders) {
        return orders.stream().map(OrderDto::new).collect(Collectors.toList());
    }

    public static Page<OrderDto> converter(Page<Orders> orders) {
        return orders.map(OrderDto::new);
    }

}
