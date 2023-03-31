package com.example.Book_Movie_Ticket.Controllers;

import com.example.Book_Movie_Ticket.DTOs.EntryDTOs.TheaterEntryDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.MovieResponseDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.TheaterResponseDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.TheaterSeatDTO;
import com.example.Book_Movie_Ticket.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add_theater")
    public ResponseEntity addTheater(@RequestBody TheaterEntryDTO theaterEntryDTO){
        String response="";
        try{
            response=theaterService.addTheater(theaterEntryDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/get_unique_locations")
    public ResponseEntity getUniqueLocation(){
        try {
            List<String> response=theaterService.getUniqueLocations();
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById")
    public ResponseEntity getById(@RequestParam("id") int id) {
        try {
            TheaterResponseDTO response = theaterService.getById(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        try {
            List<TheaterResponseDTO> response = theaterService.getAll();
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getTheatersInLocation")
    public ResponseEntity getTheatersInLocation(@RequestParam("location") String location) {
        try {
            List<TheaterResponseDTO> response = theaterService.getTheatersInLocation(location);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getMoviesAvailableInLocation")
    public ResponseEntity getMoviesAvailableInLocation(@RequestParam("location") String location) {
        try {
            List<MovieResponseDTO> response = theaterService.getMoviesAvailableInLocation(location);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

}