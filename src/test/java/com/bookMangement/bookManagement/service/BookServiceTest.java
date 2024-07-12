package com.bookMangement.bookManagement.service;

import com.bookMangement.bookManagement.entity.Book;
import com.bookMangement.bookManagement.entity.Publisher;
import com.bookMangement.bookManagement.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @InjectMocks
    private BookService bService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBook() {
        // Arrange
        List<Book> books = List.of(new Book(), new Book());
        when(bookRepository.findAll()).thenReturn(books);

        // Act
        List<Book> result = bService.getAllBook();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify (bookRepository, times(1)).findAll();

    }

    @Test
    void save() {
    }

    @Test
    void getBookById() {
    }

    @Test
    void deleteById() {
    }
}