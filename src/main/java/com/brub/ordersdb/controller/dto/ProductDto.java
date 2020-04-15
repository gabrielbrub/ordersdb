package com.brub.ordersdb.controller.dto;

import com.brub.ordersdb.modelo.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDto {
    private Long id;
    private String name;
    private double price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public static List<ProductDto> converter(List<Product> products) {
        return products.stream().map(ProductDto::new).collect(Collectors.toList());
    }
}
