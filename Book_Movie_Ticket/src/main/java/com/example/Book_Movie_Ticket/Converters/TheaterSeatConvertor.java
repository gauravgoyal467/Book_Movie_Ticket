package com.example.Book_Movie_Ticket.Converters;


import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.TheaterSeatDTO;
import com.example.Book_Movie_Ticket.Models.TheaterSeat;

public class TheaterSeatConvertor {

    public static TheaterSeatDTO convertEntityToResponseDTO(TheaterSeat theaterSeat) {
        TheaterSeatDTO theaterSeatDTO=TheaterSeatDTO.builder()
                .id(theaterSeat.getId())
                .seatType(theaterSeat.getSeatType().toString())
                .SeatNo(theaterSeat.getSeatNo())
                .build();

        return theaterSeatDTO;
    }
}