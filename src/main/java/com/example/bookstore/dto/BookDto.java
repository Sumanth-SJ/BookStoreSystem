package com.example.bookstore.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BookDto {
    private Long id;
    private String title;
    private String authors;
    private String genre;
    private String isbn;
    private BigDecimal price;
    private String description;
    private Integer stockQuantity;
    private String imageUrl;
}
