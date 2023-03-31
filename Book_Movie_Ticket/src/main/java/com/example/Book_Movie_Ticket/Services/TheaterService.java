package com.example.Book_Movie_Ticket.Services;

import com.example.Book_Movie_Ticket.Converters.MovieConvertor;
import com.example.Book_Movie_Ticket.Converters.TheaterConvertor;
import com.example.Book_Movie_Ticket.DTOs.EntryDTOs.TheaterEntryDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.MovieResponseDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.TheaterResponseDTO;
import com.example.Book_Movie_Ticket.Enums.SeatType;
import com.example.Book_Movie_Ticket.Models.*;
import com.example.Book_Movie_Ticket.Repositories.MovieRepository;
import com.example.Book_Movie_Ticket.Repositories.TheaterRepository;
import com.example.Book_Movie_Ticket.Repositories.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    TheaterSeatRepository theaterSeatRepository;
    @Autowired
    MovieRepository movieRepository;

    public String addTheater(TheaterEntryDTO theaterEntryDTO){

        Theater theater= TheaterConvertor.ConvertDtoToEntity(theaterEntryDTO);

        List<TheaterSeat> theaterSeatList=crateTheaterSeats(theaterEntryDTO, theater);

        theater.setTheaterSeatList(theaterSeatList);

        theaterRepository.save(theater);

        return "Theater and theater Seats created";
    }

    private List<TheaterSeat> crateTheaterSeats(TheaterEntryDTO theaterEntryDTO, Theater theater) {

        int normalSeatCount=theaterEntryDTO.getNormalSeatCount();
        int premiumSeatCount=theaterEntryDTO.getPremiumSeatCount();

        List<TheaterSeat> theaterSeatList=new ArrayList<>();

        //creating normal seats
        for(int i=1;i<=normalSeatCount;i++){
            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .SeatNo(i+"N")
                    .seatType(SeatType.normal)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
        }
        //crating premium seats
        for(int i=1;i<=premiumSeatCount;i++){
            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .SeatNo(i+"P")
                    .seatType(SeatType.premium)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
        }

        theaterSeatRepository.saveAll(theaterSeatList);

        return theaterSeatList;
    }

    public List<String> getUniqueLocations() {
        Set<String> response=new HashSet<>();

        List<Theater> theaterList=theaterRepository.findAll();

        for (Theater theater:theaterList){
            response.add(theater.getLocation());
        }

        return new ArrayList<>(response);
    }

    public TheaterResponseDTO getById(int id) {
        Theater theater=theaterRepository.findById(id).get();
        TheaterResponseDTO theaterResponseDTO=TheaterConvertor.convertEntityToResponseDTO(theater);

        return theaterResponseDTO;
    }

    public List<TheaterResponseDTO> getAll() {
        List<TheaterResponseDTO> responseDTOList=new ArrayList<>();

        for(Theater theater:theaterRepository.findAll()){
            responseDTOList.add(TheaterConvertor.convertEntityToResponseDTO(theater));
        }

        return responseDTOList;
    }


    public List<TheaterResponseDTO> getTheatersInLocation(String location) {
        List<TheaterResponseDTO> response=new ArrayList<>();

        List<Theater> theaterList=theaterRepository.findByLocation(location);

        for(Theater theater:theaterList){
            response.add(TheaterConvertor.convertEntityToResponseDTO(theater));
        }

        return  response;
    }


    public List<MovieResponseDTO> getMoviesAvailableInLocation(String location) {
        List<MovieResponseDTO> response = new ArrayList<>();

        List<Theater> theaterList=theaterRepository.findByLocation(location);
        Set<Integer> movieIds=new HashSet<>();

        for (Theater theater:theaterList){
            for (Show show:theater.getListOfShows()){
                movieIds.add(show.getMovie().getId());
            }
        }

        for(Integer id:movieIds){
            Movie movie=movieRepository.findById(id).get();
            response.add(MovieConvertor.convertEntityToResponseDTO(movie));
        }

        return response;
    }
}