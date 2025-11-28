package com.example.bookstore.repository;

import com.example.bookstore.entity.Book;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Book> findByAuthorsContainingIgnoreCase(String authors, Pageable pageable);
    Page<Book> findByGenreIgnoreCase(String genre, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(concat('%',:q,'%')) OR LOWER(b.authors) LIKE LOWER(concat('%',:q,'%'))")
    Page<Book> searchAllFields(@Param("q") String q, Pageable pageable);
}
