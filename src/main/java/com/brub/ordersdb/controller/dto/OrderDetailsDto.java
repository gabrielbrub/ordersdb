package com.brub.ordersdb.controller.dto;

import com.brub.ordersdb.modelo.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderDetailsDto {

    private  Map<String, Integer> itemsMap = new HashMap<>();
    private LocalDateTime date;
    private String customerName;
    private OrderStatus status;




    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderDetailsDto(Orders order){
        List<Item> itemList = order.getItems();




        itemList.forEach((i) -> {
            itemsMap.put(i.getProduct().getName(), i.getAmount());
        });
        //itemList = order.getItems().stream().map(ItemDetailsDto::new).collect(Collectors.toList());
        date = order.getOrderDate();
        customerName = order.getCustomer().getName();
        status = order.getOrderStatus();
    }

    public static List<OrderDetailsDto> converter(List<Orders> orders) {
        return orders.stream().map(OrderDetailsDto::new).collect(Collectors.toList());
    }

}
