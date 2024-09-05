package com.example.demo.controller;

import com.example.demo.model.MovieDetail;
import com.example.demo.repository.MovieDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieDetailRepository movieDetailsRepository;

    @PostMapping("/movies")
    public MovieDetail addMovieDetails(@RequestBody MovieDetail movieDetail) {
        MovieDetail updatedMovieDetail = movieDetailsRepository.createMovieDetail(movieDetail);
        return updatedMovieDetail;
    }

    @GetMapping("/movies")
    public List<MovieDetail> getAllMovieDetails() {
        return (List<MovieDetail>) movieDetailsRepository.getMovieDetailList();
    }

    @GetMapping("/movies/{id}")
    public MovieDetail getOneMovie(@PathVariable("id") String id) {
        return movieDetailsRepository.getMovieDetailById(id);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteOneMovie(@PathVariable("id") String id) {
        movieDetailsRepository.deleteMovieDetail(id);
    }
}