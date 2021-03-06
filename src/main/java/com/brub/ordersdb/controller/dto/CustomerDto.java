package com.brub.ordersdb.controller.dto;


import java.util.List;
import java.util.stream.Collectors;

import com.brub.ordersdb.model.Customer;

public class CustomerDto {

    private long id;
    private String name;
    private String phoneNumber;
    private String address;
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
        this.phoneNumber = customer.getPhoneNumber();
        this.address = customer.getAddress();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public static List<CustomerDto> converter(List<Customer> customers) {
        return customers.stream().map(CustomerDto::new).collect(Collectors.toList());
    }

}