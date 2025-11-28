package com.example.bookstore.service.impl;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository repo;

    public BookServiceImpl(BookRepository repo) { this.repo = repo; }

    private Book dtoToEntity(BookDto dto) {
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .authors(dto.getAuthors())
                .genre(dto.getGenre())
                .isbn(dto.getIsbn())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .stockQuantity(dto.getStockQuantity())
                .imageUrl(dto.getImageUrl())
                .build();
    }

    @Override
    public Book createBook(BookDto dto) {
        Book book = dtoToEntity(dto);
        return repo.save(book);
    }

    @Override
    public Book updateBook(Long id, BookDto dto) {
        Book existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        existing.setTitle(dto.getTitle());
        existing.setAuthors(dto.getAuthors());
        existing.setGenre(dto.getGenre());
        existing.setIsbn(dto.getIsbn());
        existing.setPrice(dto.getPrice());
        existing.setDescription(dto.getDescription());
        existing.setStockQuantity(dto.getStockQuantity());
        existing.setImageUrl(dto.getImageUrl());
        return repo.save(existing);
    }

    @Override
    public void deleteBook(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Book not found");
        repo.deleteById(id);
    }

    @Override
    public Book getBookById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public Page<Book> listBooks(int page, int size, String search, String genre, String sortBy, String direction) {
        Sort sort = Sort.by(sortBy == null ? "title" : sortBy);
        sort = ("desc".equalsIgnoreCase(direction)) ? sort.descending() : sort.ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if (search != null && !search.isBlank()) {
            return repo.searchAllFields(search, pageable);
        }
        if (genre != null && !genre.isBlank()) {
            return repo.findByGenreIgnoreCase(genre, pageable);
        }
        return repo.findAll(pageable);
    }
}
