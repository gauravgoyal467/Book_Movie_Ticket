package com.example.Book_Movie_Ticket.DTOs.EntryDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketEntryDTO {

    private int userId;
    private int showId;
    private List<Integer> requestedSeats;

}
