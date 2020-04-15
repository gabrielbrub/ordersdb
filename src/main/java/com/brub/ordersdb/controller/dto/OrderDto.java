package com.brub.ordersdb.controller.dto;

import com.brub.ordersdb.modelo.Customer;
import com.brub.ordersdb.modelo.Item;
import com.brub.ordersdb.modelo.OrderStatus;
import com.brub.ordersdb.modelo.Orders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class OrderDto {
//    private Customer customer;
//    private List<Item> itemList;
    private LocalDateTime date;
    private OrderStatus status;
    private List<Item> items = new ArrayList<>();



    public OrderDto(Orders o){
//        this.itemList = o.getItems();
//        this.customer = o.getCustomer();
        this.status = o.getStatus();
        this.items = o.getItems();
        this.date = o.getOrderDate();
    }

    public static List<OrderDto> converter(List<Orders> orders) {
        return orders.stream().map(OrderDto::new).collect(Collectors.toList());
    }

//    public static OrderDto converter(Orders order) {
//        return new OrderDto(order);
//    }

}
