package com.brub.ordersdb.controller.form;

import com.brub.ordersdb.model.Customer;
import com.brub.ordersdb.model.User;
import com.brub.ordersdb.repository.CustomerRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustomerForm {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String cpf;

    @NotNull @NotEmpty
    private String address;

    @NotNull @NotEmpty
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Customer convert() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new Customer(cpf, name, phoneNumber, address, user);
    }

    public Customer update(Long id, CustomerRepository customerRepository) {
        Customer customer = customerRepository.getOne(id);
        customer.setCpf(this.cpf);
        customer.setName(this.name);
        customer.setAddress(this.address);
        customer.setPhoneNumber(this.phoneNumber);
        return customer;
    }
}
