package com.example.Book_Movie_Ticket.DTOs.EntryDTOs;

import com.example.Book_Movie_Ticket.Enums.Genre;
import com.example.Book_Movie_Ticket.Enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntryDTO {

    private String name;

    private Genre genre;

    private double rating;

    private int duration;

    private Language language;

}