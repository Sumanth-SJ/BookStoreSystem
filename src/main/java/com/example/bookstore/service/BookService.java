package com.example.bookstore.service;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.entity.Book;
import org.springframework.data.domain.*;

public interface BookService {

    Book createBook(BookDto dto);

    Book updateBook(Long id, BookDto dto);

    void deleteBook(Long id);

    Book getBookById(Long id);

    Page<Book> listBooks(int page, int size, String search, String genre, String sortBy, String direction);
}
