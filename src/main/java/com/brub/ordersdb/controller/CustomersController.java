package com.brub.ordersdb.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.brub.ordersdb.controller.dto.CustomerDto;
import com.brub.ordersdb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/customers", produces={"application/json; charset=UTF-8"})
public class CustomersController {

    @Autowired
    private com.brub.ordersdb.repository.CustomerRepository customerRepository;

    @GetMapping
    public List<CustomerDto> lista(String name) {
        if (name == null) {
            List<Customer> Customers = customerRepository.findAll();
            return CustomerDto.converter(Customers);
        } else {
            List<Customer> Customers = customerRepository.findByName(name);
            return CustomerDto.converter(Customers);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CustomerDto> register(@RequestBody @Valid CustomerForm form, UriComponentsBuilder uriBuilder) {
        Customer customer = form.convert();
        customerRepository.save(customer);


        URI uri = uriBuilder.path("/Customers/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(new CustomerDto(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> detail(@PathVariable Long id) {
        Optional<Customer> Customer = customerRepository.findById(id);
        if (Customer.isPresent()) {
            return ResponseEntity.ok(new CustomerDto(Customer.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CustomerDto> update(@PathVariable Long id, @RequestBody @Valid CustomerForm form) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            Customer Customer = form.update(id, customerRepository);
            return ResponseEntity.ok(new CustomerDto(Customer));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            customerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}