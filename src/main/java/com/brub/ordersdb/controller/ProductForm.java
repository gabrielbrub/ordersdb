package com.brub.ordersdb.controller;
import com.brub.ordersdb.model.Product;
import com.brub.ordersdb.repository.ProductRepository;



import javax.validation.constraints.NotNull;


public class ProductForm {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NotNull
    private String name;

    @NotNull
    private double price;

    public Product convert() {
        return new Product(name, price);
    }

    public Product update(Long id, ProductRepository productRepository) {
        Product product = productRepository.getOne(id);
        product.setPrice(price);
        product.setName(name);
        return product;
    }
}
