package com.example.bookstore.service.impl;

import com.example.bookstore.dto.OrderItemDto;
import com.example.bookstore.dto.OrderRequest;
import com.example.bookstore.entity.*;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final BookRepository bookRepo;

    public OrderServiceImpl(OrderRepository orderRepo, BookRepository bookRepo) {
        this.orderRepo = orderRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public OrderEntity placeOrder(OrderRequest req) {
        List<OrderItem> items = new ArrayList<>();
        double total = 0.0;
        for (OrderItemDto it : req.getItems()) {
            Book b = bookRepo.findById(it.getBookId()).orElseThrow(() -> new RuntimeException("Book not found: " + it.getBookId()));
            if (b.getStockQuantity() < it.getQuantity()) throw new RuntimeException("Insufficient stock for book: " + b.getTitle());
            b.setStockQuantity(b.getStockQuantity() - it.getQuantity());
            bookRepo.save(b);
            OrderItem oi = OrderItem.builder().bookId(b.getId()).quantity(it.getQuantity()).price(b.getPrice().doubleValue()).build();
            items.add(oi);
            total += b.getPrice().doubleValue() * it.getQuantity();
        }
        OrderEntity order = OrderEntity.builder()
                .customerName(req.getCustomerName())
                .customerEmail(req.getCustomerEmail())
                .items(items)
                .totalPrice(total)
                .status(OrderStatus.PENDING)
                .build();
        return orderRepo.save(order);
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }
}
