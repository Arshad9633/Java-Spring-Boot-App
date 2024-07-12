package com.bookMangement.bookManagement.controller;

import com.bookMangement.bookManagement.entity.Publisher;
import com.bookMangement.bookManagement.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/add_Publisher")
    public String addPublisher(Model model){
        model.addAttribute("publisher", new Publisher());
        return "addPublisher";
    }

    @PostMapping("/savePublisher")
    public String savePublisher(@ModelAttribute("publisher") Publisher publisher, Model model) {
        try {
            publisherService.savePublisher(publisher);
            return "redirect:/publishers"; // Redirect if save is successful
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            model.addAttribute("publisher", publisher); // Add publisher back to the model for form re-rendering
            return "addPublisher"; // Return to the addPublisher view with error message
        }
    }

    @GetMapping("/editPublisher/{id}")
    public String editPublisher(@PathVariable("id") int id, Model model){
        Publisher publisher = publisherService.getPublisherById(id);
        model.addAttribute("publisher", publisher);
        return "editPublisher";
    }

    @GetMapping("/deletePublisher/{id}")
    public String deletePublisher(@PathVariable("id") int id) {
        publisherService.deletePublisherById(id);
        return "redirect:/publishers";
    }

    @GetMapping("/publishers")
    public String getAllPublishers(Model model) {
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("publishers", publishers);
        return "publisherList";
    }
}
