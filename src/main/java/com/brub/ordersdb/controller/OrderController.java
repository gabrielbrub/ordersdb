package com.brub.ordersdb.controller;


import com.brub.ordersdb.controller.dto.OrderDto;
import com.brub.ordersdb.controller.form.OrderForm;
import com.brub.ordersdb.controller.form.OrderUpdateForm;
import com.brub.ordersdb.model.OrderStatus;
import com.brub.ordersdb.model.Orders;
import com.brub.ordersdb.model.User;
import com.brub.ordersdb.repository.CustomerRepository;
import com.brub.ordersdb.repository.OrderRepository;
import com.brub.ordersdb.repository.ProductRepository;
import com.brub.ordersdb.repository.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/orders")
@Scope(value = "request")
public class OrderController {

    private User user;
    @PostConstruct
    public void init() {
        this.user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductRepository itemRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public List<OrderDto> lista(String cpf) {
            List<Orders> Orders = orderRepository.findByStatus(OrderStatus.ACTIVE);
            return OrderDto.converter(Orders);
    }



    @PostMapping
    @Transactional
    public ResponseEntity<OrderDto> save(@RequestBody @Valid OrderForm form, UriComponentsBuilder uriBuilder) {
        Orders order = form.convert(customerRepository, productRepository);
        orderRepository.save(order);

        URI uri = uriBuilder.path("/Orders/{id}").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(uri).body(new OrderDto(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> detail(@PathVariable Long id) {
        Optional<Orders> order = orderRepository.findByIdAndUserId(id, user.getId());
        if (order.isPresent()) {
            return ResponseEntity.ok(new OrderDto(order.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/history")
    public Page<OrderDto> history(@PageableDefault(sort = "orderDate", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        Page<Orders> ordersCompleted = orderRepository.findByStatusNotAndUserId(OrderStatus.ACTIVE, user.getId(), paginacao);
        return OrderDto.converter(ordersCompleted);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<OrderDto> update(@PathVariable Long id, @RequestBody @Valid OrderUpdateForm form) {
        Optional<Orders> optional = orderRepository.findByIdAndUserId(id, user.getId());
        if (optional.isPresent()) {
            Orders Order = form.update(id, orderRepository, productRepository, customerRepository);
            return ResponseEntity.ok(new OrderDto(Order));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Orders> optional = orderRepository.findByIdAndUserId(id, user.getId());
        if (optional.isPresent()) {
            orderRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
