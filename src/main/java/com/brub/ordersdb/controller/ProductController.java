package com.brub.ordersdb.controller;


import com.brub.ordersdb.controller.dto.CustomerDto;
import com.brub.ordersdb.controller.dto.ProductDto;

import com.brub.ordersdb.modelo.Customer;
import com.brub.ordersdb.modelo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private com.brub.ordersdb.repository.ProductRepository productRepository;

    @GetMapping
    public List<ProductDto> lista(String name) {
        if (name == null) {
            List<Product> Products = productRepository.findAll();
            return ProductDto.converter(Products);
        } else {
            List<Product> Products = new ArrayList<>();
            Product Product = productRepository.findByName(name);
            Products.add(Product);
            return ProductDto.converter(Products);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProductDto> cadastrar(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
        Product product = form.convert();
        productRepository.save(product);


        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDto(product));
    }

}
