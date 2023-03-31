package com.example.Book_Movie_Ticket.DTOs.ResponseDTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponseDTO {
    private int id;
    private String name;
    private String genre;
    private double rating;
    private int duration;
    private String language;
}