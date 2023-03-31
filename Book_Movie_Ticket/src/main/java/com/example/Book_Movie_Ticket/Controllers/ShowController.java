package com.example.Book_Movie_Ticket.Controllers;


import com.example.Book_Movie_Ticket.DTOs.EntryDTOs.ShowEntryDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.ShowResponseDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.ShowSeatResponseDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.TicketResponseDTO;
import com.example.Book_Movie_Ticket.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add_show")
    public ResponseEntity addShow(@RequestBody ShowEntryDTO showEntryDTO){
        try {
            String response=showService.addShow(showEntryDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CREATED);
        }
    }

    @GetMapping("/get_show_time")
    public ResponseEntity getShowTime(@RequestParam("movieId")int movieId, @RequestParam("theaterId") int theaterId){
        try {
            List<String> response=showService.getShowTime(movieId, theaterId);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getById")
    public ResponseEntity getById(@RequestParam("id") int id) {
        try {
            ShowResponseDTO response = showService.getById(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        try {
            List<ShowResponseDTO> response = showService.getAll();
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAvailableSeats")
    public ResponseEntity getAvailableSeats(@RequestParam("id") int id) {
        try {
            List<ShowSeatResponseDTO> response = showService.getAvailableSeats(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getSalesForShow")
    public ResponseEntity getSalesForShow(@RequestParam("id") int id) {
        try {
            Long response = showService.getSalesForShow(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getBookedTicketsForShow")
    public ResponseEntity getBookedTicketsForShow(@RequestParam("id") int id) {
        try {
            List<TicketResponseDTO> response = showService.getBookedTicketsForShow(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getShowsForMovieAndLocation")
    public ResponseEntity getShowsForMovieAndLocation(@RequestParam("id") int movieId, @RequestParam("location") String location) {
        try {
            List<ShowResponseDTO> response = showService.getShowsForMovieAndLocation(movieId, location);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getShowsForDate")
    public ResponseEntity getShowsForDate(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<ShowResponseDTO> response = showService.getShowsForDate(date);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

}