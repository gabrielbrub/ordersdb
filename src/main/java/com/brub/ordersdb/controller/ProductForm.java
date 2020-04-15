package com.brub.ordersdb.controller;

import com.brub.ordersdb.modelo.Product;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class ProductForm {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @NotNull
    private String name;

    @NotNull
    private int price;

    public Product convert() {
        return new Product(name, price);
    }
}
