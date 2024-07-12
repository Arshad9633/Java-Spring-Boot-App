package com.bookMangement.bookManagement.controller;

import com.bookMangement.bookManagement.entity.Book;
import com.bookMangement.bookManagement.entity.Genre;
import com.bookMangement.bookManagement.entity.Publisher;
import com.bookMangement.bookManagement.service.BookService;
import com.bookMangement.bookManagement.service.GenreService;
import com.bookMangement.bookManagement.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/add_Books")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        List<Genre> genres = genreService.getAllGenres();
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("genres", genres);
        model.addAttribute("publishers", publishers);
        return "addBook";
    }

    @GetMapping("/availableBooks")
    public String getAllBook(Model model){
        List<Book> books = bookService.getAllBook();
        model.addAttribute("books", books);
        return "bookList";
    }

    @PostMapping("/save")
    public String addBooks(@ModelAttribute("book") Book book, @RequestParam("publisherId") int publisherId, Model model){
        try{
            Publisher publisher = publisherService.getPublisherById(publisherId);
            book.setPublisher(publisher);
            bookService.save(book);
            return "redirect:/availableBooks";
        } catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            model.addAttribute("book", book);
            return "addBook";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookService.deleteById(id);
        return "redirect:/availableBooks";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        List<Genre> genres = genreService.getAllGenres();
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("genres", genres);
        model.addAttribute("publishers", publishers);
        return "editBook";
    }
}
