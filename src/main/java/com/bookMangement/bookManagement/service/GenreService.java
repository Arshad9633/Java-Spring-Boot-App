package com.bookMangement.bookManagement.service;


import com.bookMangement.bookManagement.entity.Genre;
import com.bookMangement.bookManagement.entity.Publisher;
import com.bookMangement.bookManagement.repository.BookRepository;
import com.bookMangement.bookManagement.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository gRepo;

    @Autowired
    private BookRepository bRepo;

    public Genre saveGenre(Genre genre){
        Optional<Genre> existingGenre = gRepo.findByName(genre.getName());
        if (existingGenre.isPresent()){
            throw  new IllegalArgumentException("Genre'" + genre.getName() + "' already exists");
        }
        return gRepo.save(genre);
    }

    public List<Genre> getAllGenres(){
        return gRepo.findAll();
    }

    public Genre getGenreByName(int id) {
        return gRepo.findById(id).orElse(null);
    }

    public void deleteGenreById(int id) {
        bRepo.setGenreToNull(id);
        gRepo.deleteById(id);
    }
}
