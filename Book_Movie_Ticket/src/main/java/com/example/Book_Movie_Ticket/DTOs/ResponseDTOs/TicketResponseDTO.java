package com.example.Book_Movie_Ticket.DTOs.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponseDTO {
    private int id;
    private String movieName;
    private String theaterName;
    private LocalDate showDate;
    private LocalTime showTime;
    private int totalAmount;
    private boolean cancelled;
    private String ticketId;

    private List<String> bookedSeats=new ArrayList<>();
}