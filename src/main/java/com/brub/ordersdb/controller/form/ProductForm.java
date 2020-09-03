package com.brub.ordersdb.controller.form;
import com.brub.ordersdb.model.Product;
import com.brub.ordersdb.model.User;
import com.brub.ordersdb.repository.ProductRepository;
import org.springframework.security.core.context.SecurityContextHolder;


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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new Product(name, price, user);
    }

    public Product update(Long id, ProductRepository productRepository) {
        Product product = productRepository.getOne(id);
        product.setPrice(price);
        product.setName(name);
        return product;
    }
}
