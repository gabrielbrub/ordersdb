package com.brub.ordersdb.controller;

import com.brub.ordersdb.modelo.Customer;
import com.brub.ordersdb.modelo.Item;
import com.brub.ordersdb.modelo.Orders;
import com.brub.ordersdb.modelo.Product;
import com.brub.ordersdb.repository.CustomerRepository;
import com.brub.ordersdb.repository.ItemRepository;
import com.brub.ordersdb.repository.ProductRepository;

import javax.persistence.criteria.Order;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderForm {
    @NotNull
    private Map<String, Integer> products;
    @NotNull
    private String date;
    @NotNull
    private String customerCPF;
//    @NotNull
//    private String productName;

    public String getCustomerCPF() {
        return customerCPF;
    }

    public void setCustomerCPF(String customerCPF) {
        this.customerCPF = customerCPF;
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


//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }

    public Orders convert(CustomerRepository customerRepository, ProductRepository productRepository) {
        Orders order = new Orders();

        List<Item> itemList = new ArrayList<>();
//        Item item = new Item(productRepository.findByName(productName), 2, order);
        //Item item = new Item();
        products.forEach((k,v) -> {
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

        order.setCustomer(customerRepository.findByCpf(customerCPF));
        return order;
//        return new Orders(customerRepository.findByCpf(customerCPF), itemList, dateTime);
    }
}
