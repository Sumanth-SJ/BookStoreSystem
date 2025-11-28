package com.example.bookstore.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class OrderItemDto {
    private Long bookId;
    private Integer quantity;
}
