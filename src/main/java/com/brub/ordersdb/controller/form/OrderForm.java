package com.brub.ordersdb.controller.form;

import com.brub.ordersdb.model.*;
import com.brub.ordersdb.repository.CustomerRepository;
import com.brub.ordersdb.repository.ProductRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderForm {
    @NotNull
    private Map<String, Integer> products;
    @NotNull
    private String date;
    @NotNull
    private String customerCpf;
    private String status;


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


    public Orders convert(CustomerRepository customerRepository, ProductRepository productRepository) {
        Orders order = new Orders();
        return getOrders(productRepository, customerRepository, order);
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private Orders getOrders(ProductRepository productRepository, CustomerRepository customerRepository, Orders order) {
        List<Item> itemList = new ArrayList<>();
        products.forEach((k, v) -> {
            Item item = new Item();
            item.setProduct(productRepository.findByName(k));
            item.setAmount(v);
            item.setOrder(order);
            itemList.add(item);
        });
        order.setItems(itemList);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUser(user);
        if(status!=null)
            order.setStatus(OrderStatus.valueOf(status.toUpperCase()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        order.setOrderDate(dateTime);
        order.setCustomer(customerRepository.findByCpf(customerCpf));

        return order;
    }
}
