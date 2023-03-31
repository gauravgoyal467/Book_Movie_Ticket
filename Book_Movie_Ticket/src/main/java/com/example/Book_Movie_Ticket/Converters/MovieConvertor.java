package com.example.Book_Movie_Ticket.Converters;


import com.example.Book_Movie_Ticket.DTOs.EntryDTOs.MovieEntryDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.MovieResponseDTO;
import com.example.Book_Movie_Ticket.Models.Movie;

public class MovieConvertor {
    public static Movie ConvertDtoToMovieEntity(MovieEntryDTO movieEntryDTO) {
        Movie movie=Movie.builder()
                .name(movieEntryDTO.getName())
                .genre(movieEntryDTO.getGenre())
                .rating(movieEntryDTO.getRating())
                .duration(movieEntryDTO.getDuration())
                .language(movieEntryDTO.getLanguage())
                .build();

        return movie;
    }

    public static MovieResponseDTO convertEntityToResponseDTO(Movie movie) {
        MovieResponseDTO movieResponseDTO= MovieResponseDTO.builder()
                .id(movie.getId())
                .name(movie.getName())
                .genre(movie.getGenre().toString())
                .rating(movie.getRating())
                .duration(movie.getDuration())
                .language(movie.getLanguage().toString())
                .build();

        return movieResponseDTO;
    }
}