package com.example.bookstore.service;

import com.example.bookstore.entity.User;
import com.example.bookstore.dto.RegisterRequest;

public interface UserService {
    User register(RegisterRequest req);
}
