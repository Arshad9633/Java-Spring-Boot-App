package com.bookMangement.bookManagement.controller;

import com.bookMangement.bookManagement.entity.Genre;
import com.bookMangement.bookManagement.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/add_Genre")
    public String addGenre(Model model){
        model.addAttribute("genre", new Genre());
        return "addGenre";
    }

    @PostMapping("/saveGenre")
    public String saveGenre(@ModelAttribute("genre") Genre genre, Model model){
        try {
            genreService.saveGenre(genre);
            return "redirect:/genres"; // Redirect if save is successful
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            model.addAttribute("genre", genre); // Add publisher back to the model for form re-rendering
            return "addGenre"; // Return to the addPublisher view with error message
        }
    }

    @GetMapping("/genres")
    public String getAllGenres(Model model){
        List<Genre> genres = genreService.getAllGenres();
        model.addAttribute("genres", genres);
       return "genreList";
    }

    @GetMapping("/deleteGenre/{id}")
    public String deleteGenre(@PathVariable("id") int id){
        genreService.deleteGenreById(id);
        return "redirect:/genres";
    }

}
