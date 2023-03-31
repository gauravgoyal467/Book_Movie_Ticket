package com.example.Book_Movie_Ticket.Controllers;


import com.example.Book_Movie_Ticket.DTOs.EntryDTOs.UserEntryDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.TicketResponseDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.UserResponseDTO;
import com.example.Book_Movie_Ticket.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody UserEntryDTO userEntryDTO) {
        try {
            String response = userService.addUser(userEntryDTO);
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("User not added", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getById")
    public ResponseEntity getById(@RequestParam("id") int id) {
        try {
            UserResponseDTO response = userService.getById(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        try {
            List<UserResponseDTO> response = userService.getAll();
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UserEntryDTO userEntryDTO, @RequestParam("id") int id) {
        try {
            String response = userService.updateUser(userEntryDTO, id);
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity("User details not updated", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/deleteById")
    public ResponseEntity deleteById(@RequestParam("id") int id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<>("User with id " + id + " deleted", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getTicketsById")
    public ResponseEntity getTicketsById(@RequestParam("id") int id) {
        try {
            List<TicketResponseDTO> response = userService.getTicketsById(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        }
    }

}
