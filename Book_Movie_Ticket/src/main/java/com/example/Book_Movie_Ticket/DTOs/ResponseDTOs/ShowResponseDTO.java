package com.example.Book_Movie_Ticket.DTOs.ResponseDTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowResponseDTO {
    private int id;
    private LocalDate showDate;
    private LocalTime showTime;
    private String showType;
    private Date createdOn;
    private MovieResponseDTO movie;
    private List<ShowSeatResponseDTO> showSeats=new ArrayList<>();
    private String theaterName;

}
