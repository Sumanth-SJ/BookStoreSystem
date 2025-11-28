package com.example.bookstore.service;

import com.example.bookstore.dto.OrderRequest;
import com.example.bookstore.entity.OrderEntity;

public interface OrderService {
    OrderEntity placeOrder(OrderRequest req);
    OrderEntity getOrderById(Long id);
}
