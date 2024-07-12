package com.bookMangement.bookManagement.service;

import com.bookMangement.bookManagement.entity.Book;
import com.bookMangement.bookManagement.entity.Genre;
import com.bookMangement.bookManagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bRepo;

    public List<Book> getAllBook(){
        return bRepo.findAll();
    }

    public void save(Book book){
        if (book.getId() == null) {
            Optional<Book> existingBook = bRepo.findByTitle(book.getTitle());
            if (existingBook.isPresent()){
                throw new IllegalArgumentException("Book '" + book.getTitle() + "' already exists");
            }
        }
        bRepo.save(book);
    }

    public Book getBookById(int id){
        return bRepo.findById(id).orElse(null);
    }

    public void deleteById(int id){
        bRepo.deleteById(id);
    }
}

