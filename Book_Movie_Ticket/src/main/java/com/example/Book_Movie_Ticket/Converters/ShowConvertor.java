package com.example.Book_Movie_Ticket.Converters;

import com.example.Book_Movie_Ticket.DTOs.EntryDTOs.ShowEntryDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.ShowResponseDTO;
import com.example.Book_Movie_Ticket.Models.Show;
import com.example.Book_Movie_Ticket.Models.ShowSeat;

import java.util.ArrayList;

public class ShowConvertor {

    public static Show convertDtotoShow(ShowEntryDTO showEntryDTO){
        Show show=Show.builder()
                .showDate(showEntryDTO.getShowDate())
                .showTime(showEntryDTO.getShowTime())
                .showType(showEntryDTO.getShowType())
                .build();

        return show;
    }

    public static ShowResponseDTO convertEntityToResponseDTO(Show show) {
        ShowResponseDTO showResponseDTO= ShowResponseDTO.builder()
                .id(show.getId())
                .showDate(show.getShowDate())
                .showTime(show.getShowTime())
                .showType(show.getShowType().toString())
                .createdOn(show.getCreatedOn())
                .movie(MovieConvertor.convertEntityToResponseDTO(show.getMovie()))
                .showSeats(new ArrayList<>())
                .theaterName(show.getTheater().getName())
                .build();

        for(ShowSeat showSeat:show.getListOfShowSeats()){
            showResponseDTO.getShowSeats().add(ShowSeatConvertor.convertEntityToResponseDTO(showSeat));
        }
        return showResponseDTO;
    }
}