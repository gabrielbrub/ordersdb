package com.brub.ordersdb.controller;


import com.brub.ordersdb.controller.dto.ProductDto;

import com.brub.ordersdb.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private com.brub.ordersdb.repository.ProductRepository productRepository;

    @GetMapping
    @Cacheable(value = "listaDeProdutos")
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
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    public ResponseEntity<ProductDto> register(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
        Product product = form.convert();
        productRepository.save(product);


        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDto(product));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody @Valid ProductForm form) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = form.update(id, productRepository);
            return ResponseEntity.ok(new ProductDto(product));
        }
        return ResponseEntity.notFound().build();
    }

}
