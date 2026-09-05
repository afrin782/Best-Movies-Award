package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DAO.MovieDAO;
import com.example.demo.model.Movies;

@Controller
public class MovieController {

    @Autowired
    private MovieDAO movieDAO;

    // READ - Display all movies
    @GetMapping("/")
    public String getAllMovies(Model model) {
        model.addAttribute("movies", movieDAO.getAllMovies());
        return "index";
    }

    // Show Add Movie page
    @GetMapping("/add")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movies());
        return "add-movie";
    }

    // CREATE - Save movie
    @PostMapping("/save")
    public String saveMovie(@ModelAttribute("movie") Movies movie) {
        movieDAO.save(movie);
        return "redirect:/";
    }

    // Show Edit Movie page
    @GetMapping("/edit/{id}")
    public String showEditMovieForm(@PathVariable int id, Model model) {
        model.addAttribute("movie", movieDAO.getMovieById(id));
        return "edit-movie";
    }

    // UPDATE - Update movie
    @PostMapping("/update")
    public String updateMovie(@ModelAttribute("movie") Movies movie) {
        movieDAO.update(movie);
        return "redirect:/";
    }

    // DELETE - Delete movie
    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable int id) {
        movieDAO.delete(id);
        return "redirect:/";
    }
}