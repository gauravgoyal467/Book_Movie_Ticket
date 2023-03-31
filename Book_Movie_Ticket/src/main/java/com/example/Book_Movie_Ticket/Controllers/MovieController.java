package com.example.Book_Movie_Ticket.Controllers;


import com.example.Book_Movie_Ticket.DTOs.EntryDTOs.MovieEntryDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.MovieResponseDTO;
import com.example.Book_Movie_Ticket.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/addMovie")
    public ResponseEntity addMovie(@RequestBody MovieEntryDTO movieEntryDTO){
        try {
            String response=movieService.addMovie(movieEntryDTO) ;
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/movie_with_max_shows")
    public ResponseEntity findMovieWithMaxShows(){
        try {
            String response=movieService.findMovieWithMaxShows();
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Error occurred", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/theaters_for_movie/{id}")
    public ResponseEntity findTheatersForMovie(@PathVariable("id") int id){
        try {
            List<String> response;
            response = movieService.findTheatersForMovie(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getById")
    public ResponseEntity getById(@RequestParam("id") int id) {
        try {
            MovieResponseDTO response = movieService.getById(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        try {
            List<MovieResponseDTO> response = movieService.getAll();
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/salesForMovieById")
    public ResponseEntity salesForMovieById(@RequestParam("id") int id) {
        try {
            Long response = movieService.salesForMovieById(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

}