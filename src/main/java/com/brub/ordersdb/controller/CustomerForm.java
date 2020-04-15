package com.brub.ordersdb.controller;

import com.brub.ordersdb.modelo.Customer;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustomerForm {
    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String name;

    @NotNull
    @NotEmpty
    private String cpf;

    @NotNull @NotEmpty @Length(min = 10)
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @NotNull @NotEmpty
    private String adress;

    public Customer convert() {
        Customer customer = new Customer(cpf, name, phoneNumber, adress);
        customer.setCpf(cpf);
        customer.setName(name);
        customer.setAddress(adress);
        customer.setPhoneNumber(phoneNumber);
        return customer;
    }
}
