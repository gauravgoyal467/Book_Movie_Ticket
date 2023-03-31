package com.example.Book_Movie_Ticket.Services;

import com.example.Book_Movie_Ticket.Converters.MovieConvertor;
import com.example.Book_Movie_Ticket.DTOs.EntryDTOs.MovieEntryDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.MovieResponseDTO;
import com.example.Book_Movie_Ticket.Models.Movie;
import com.example.Book_Movie_Ticket.Models.Show;
import com.example.Book_Movie_Ticket.Models.Ticket;
import com.example.Book_Movie_Ticket.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;


    public String addMovie(MovieEntryDTO movieEntryDTO) {
        Movie movie= MovieConvertor.ConvertDtoToMovieEntity(movieEntryDTO);

        movieRepository.save(movie);

        return "Movie added successfully";
    }

    public String findMovieWithMaxShows() {
        List<Movie> movieList=movieRepository.findAll();

        Movie movieWithMaxShows=null;
        int maxShows=0;

        for(Movie movie:movieList){
            if(movie.getListOfShows().size()>maxShows){
                movieWithMaxShows=movie;
                maxShows=movie.getListOfShows().size();
            }
        }

        if(movieWithMaxShows==null) return "no movie shows found";

        return movieWithMaxShows.getName();
    }

    public List<String> findTheatersForMovie(int movieId) {
        Movie movie=movieRepository.findById(movieId).get();

        List<String> response=new ArrayList<>();

        for(Show show:movie.getListOfShows()){
            response.add(show.getTheater().getName());
        }

        return response;
    }

    public MovieResponseDTO getById(int id) {
        Movie movie=movieRepository.findById(id).get();

        MovieResponseDTO movieResponseDTO=MovieConvertor.convertEntityToResponseDTO(movie);

        return movieResponseDTO;
    }

    public List<MovieResponseDTO> getAll() {
        List<MovieResponseDTO> response =new ArrayList<>();

        for(Movie movie:movieRepository.findAll()){
            MovieResponseDTO movieResponseDTO=MovieConvertor.convertEntityToResponseDTO(movie);
            response.add(movieResponseDTO);
        }

        return response;
    }

    public Long salesForMovieById(int id) {
        Long sales=0L;

        Movie movie=movieRepository.findById(id).get();
        for(Show show:movie.getListOfShows()){
            for(Ticket ticket:show.getListOfTickets()){
                if(!ticket.isCancelled()){
                    sales+=ticket.getTotalAmount();
                }
            }
        }

        return sales;
    }
}