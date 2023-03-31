package com.example.Book_Movie_Ticket.Converters;


import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.ShowSeatResponseDTO;
import com.example.Book_Movie_Ticket.Models.ShowSeat;

public class ShowSeatConvertor {

    public static ShowSeatResponseDTO convertEntityToResponseDTO(ShowSeat showSeat) {
        ShowSeatResponseDTO showSeatResponseDTO=ShowSeatResponseDTO.builder()
                .id(showSeat.getId())
                .booked(showSeat.isBooked())
                .price(showSeat.getPrice())
                .seatType(showSeat.getSeatType().toString())
                .bookedOn(showSeat.getBookedOn())
                .build();

        return showSeatResponseDTO;
    }
}