//package com.brub.ordersdb.controller.dto;
//
//import com.brub.ordersdb.modelo.Customer;
//import com.brub.ordersdb.modelo.Item;
//import com.brub.ordersdb.modelo.OrderStatus;
//import com.brub.ordersdb.modelo.Orders;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//
//public class OrderDto {
//    //    private Customer customer;
//    private List<Item> items;
//    private Long id;
//    private String date;
//    private OrderStatus status;
//    //private Map<String, Integer> items = new HashMap<String, Integer>();
//    private LocalDateTime creationDate;
//    private Customer customer;
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public OrderStatus getStatus() {
//        return status;
//    }
//
//    public List<Item> getItems() {
//        return items;
//    }
//
//    public LocalDateTime getCreationDate() {
//        return creationDate;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public OrderDto(Orders o){
////        this.itemList = o.getItems();
////        this.customer = o.getCustomer();
//        this.id = o.getId();
//        this.status = o.getStatus();
//        this.items = o.getItems();
////        o.getItems().stream().forEach((i) -> {
////            items.put(i.getProduct().getName(), i.getAmount());
////        });
//        this.date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(o.getOrderDate());
//        this.creationDate = o.getCreationDate();
//        this.customer = o.getCustomer();
//    }
//
//    public static List<OrderDto> converter(List<Orders> orders) {
//        return orders.stream().map(OrderDto::new).collect(Collectors.toList());
//    }
//
//}


//
//package com.brub.ordersdb.controller;
//
//
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class HelloController {
//
//    @RequestMapping("/")
//    @ResponseBody
//    public String hello() {
//        return "Hello World!";
//    }
//
//}
//
