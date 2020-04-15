package com.brub.ordersdb.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.brub.ordersdb.controller.dto.CustomerDto;
import com.brub.ordersdb.modelo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/customers")
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
    public ResponseEntity<CustomerDto> cadastrar(@RequestBody @Valid CustomerForm form, UriComponentsBuilder uriBuilder) {
        Customer customer = form.convert();
        customerRepository.save(customer);


        URI uri = uriBuilder.path("/Customers/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(new CustomerDto(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> detalhar(@PathVariable Long id) {
        Optional<Customer> Customer = customerRepository.findById(id);
        if (Customer.isPresent()) {
            return ResponseEntity.ok(new CustomerDto(Customer.get()));
        }

        return ResponseEntity.notFound().build();
    }

//    @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<CustomerDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoCustomerForm form) {
//        Optional<Customer> optional = CustomerRepository.findById(id);
//        if (optional.isPresent()) {
//            Customer Customer = form.atualizar(id, CustomerRepository);
//            return ResponseEntity.ok(new CustomerDto(Customer));
//        }
//
//        return ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    public ResponseEntity<?> remover(@PathVariable Long id) {
//        Optional<Customer> optional = CustomerRepository.findById(id);
//        if (optional.isPresent()) {
//            CustomerRepository.deleteById(id);
//            return ResponseEntity.ok().build();
//        }
//
//        return ResponseEntity.notFound().build();
//    }

}