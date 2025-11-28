package com.example.bookstore.dto;

import lombok.*;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderRequest {
    private String customerName;
    private String customerEmail;
    private List<OrderItemDto> items;
}
