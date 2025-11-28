package com.example.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "books")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String authors;

    private String genre;
    private String isbn;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(length = 2000)
    private String description;

    private Integer stockQuantity;

    private String imageUrl;
}
