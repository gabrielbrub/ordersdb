package com.brub.ordersdb.controller;


import com.brub.ordersdb.controller.dto.ProductDto;

import com.brub.ordersdb.controller.form.ProductForm;
import com.brub.ordersdb.model.Product;
import com.brub.ordersdb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/products")
@Scope(value = "request")
public class ProductController {

    private User user;
    @PostConstruct
    public void init() {
        this.user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Autowired
    private com.brub.ordersdb.repository.ProductRepository productRepository;

    @GetMapping
    @Cacheable(value = "listaDeProdutos", keyGenerator = "customKeyGenerator")
    public List<ProductDto> lista() {
            List<Product> Products = productRepository.findByUserId(user.getId());
            return ProductDto.converter(Products);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    public ResponseEntity<ProductDto> save(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
        Product product = form.convert();
        productRepository.save(product);

        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDto(product));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeProdutos", allEntries = true)
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Product> optional = productRepository.findByIdAndUserId(id, user.getId());
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
        Optional<Product> optional = productRepository.findByIdAndUserId(id, user.getId());
        if (optional.isPresent()) {
            Product product = form.update(id, productRepository);
            return ResponseEntity.ok(new ProductDto(product));
        }
        return ResponseEntity.notFound().build();
    }

}
