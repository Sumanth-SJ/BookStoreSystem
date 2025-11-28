package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderRequest;
import com.example.bookstore.entity.OrderEntity;
import com.example.bookstore.service.OrderService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<OrderEntity> placeOrder(@RequestBody OrderRequest req) {
        OrderEntity order = orderService.placeOrder(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrder(@PathVariable Long id) {
        OrderEntity order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
}
