package com.brub.ordersdb.controller;

import com.brub.ordersdb.controller.dto.OrderDetailsDto;
import com.brub.ordersdb.controller.dto.OrderDto;
import com.brub.ordersdb.modelo.Orders;
import com.brub.ordersdb.repository.CustomerRepository;
import com.brub.ordersdb.repository.ItemRepository;
import com.brub.ordersdb.repository.OrderRepository;
import com.brub.ordersdb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;


    @GetMapping
    public List<OrderDto> lista(String name) {
        if (name == null) {
            List<Orders> Orders = orderRepository.findAll();
            return OrderDto.converter(Orders);
        } else {
            List<Orders> Orders = orderRepository.findByCustomerName(name);
            return OrderDto.converter(Orders);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OrderDetailsDto> cadastrar(@RequestBody @Valid OrderForm form, UriComponentsBuilder uriBuilder) {
        Orders order = form.convert(customerRepository, productRepository);
        orderRepository.save(order);


        URI uri = uriBuilder.path("/Orders/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(new OrderDetailsDto(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsDto> detalhar(@PathVariable Long id) {
        Optional<Orders> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(new OrderDetailsDto(order.get()));
        }

        return ResponseEntity.notFound().build();
    }
}
