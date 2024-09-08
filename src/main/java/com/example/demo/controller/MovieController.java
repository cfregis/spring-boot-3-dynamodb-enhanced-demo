package com.example.demo.controller;

import com.example.demo.model.Movie;
import com.example.demo.producer.MovieProducer;
import com.example.demo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository moviesRepository;

    @Autowired
    private MovieProducer movieProducer;

    @PostMapping("/movies")
    public void addMovies(@RequestBody Movie movie) {
        movieProducer.send(movie);
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return (List<Movie>) moviesRepository.getMovieList();
    }

    @GetMapping("/movies/{title}/{year}")
    public Movie getOneMovie(@PathVariable("title") String title, @PathVariable("year") Integer year) {
        return moviesRepository.getMovieById(title,year);
    }

    @DeleteMapping("/movies/{title}/{year}")
    public void deleteOneMovie(@PathVariable("title") String title, @PathVariable("year") Integer year) {
        moviesRepository.deleteMovie(title,year);
    }
}