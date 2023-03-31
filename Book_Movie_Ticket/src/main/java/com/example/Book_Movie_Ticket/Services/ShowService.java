package com.example.Book_Movie_Ticket.Services;

import com.example.Book_Movie_Ticket.Converters.ShowConvertor;
import com.example.Book_Movie_Ticket.Converters.ShowSeatConvertor;
import com.example.Book_Movie_Ticket.Converters.TicketConvertor;
import com.example.Book_Movie_Ticket.DTOs.EntryDTOs.ShowEntryDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.ShowResponseDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.ShowSeatResponseDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.TicketResponseDTO;
import com.example.Book_Movie_Ticket.Enums.SeatType;
import com.example.Book_Movie_Ticket.Models.*;
import com.example.Book_Movie_Ticket.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ShowSeatRepository showSeatRepository;

    public String addShow(ShowEntryDTO showEntryDTO){

        Show show= ShowConvertor.convertDtotoShow(showEntryDTO);

        List<ShowSeat> showSeatsList=createShowSeatsList(showEntryDTO, show);

        show.setListOfShowSeats(showSeatsList);
        showRepository.save(show);

        Movie movie=movieRepository.findById(showEntryDTO.getMovieId()).get();

        movie.getListOfShows().add(show);
        show.setMovie(movie);

        Theater theater=theaterRepository.findById(showEntryDTO.getTheaterId()).get();

        theater.getListOfShows().add(show);
        show.setTheater(theater);

        movieRepository.save(movie);
        theaterRepository.save(theater);
        //showRepository.save(show);

        return "show has been created";
    }

    private List<ShowSeat> createShowSeatsList(ShowEntryDTO showEntryDTO, Show show) {
        List<ShowSeat> showSeatsList=new ArrayList<>();

        int normalSeatPrice=showEntryDTO.getNormalSeatPrice();
        int premiumSeatPrice=showEntryDTO.getPremiumSeatPrice();

        Theater theater=theaterRepository.findById(showEntryDTO.getTheaterId()).get();
        List<TheaterSeat> theaterSeatList=theater.getTheaterSeatList();

        for(TheaterSeat theaterSeat:theaterSeatList){
            ShowSeat showSeats= ShowSeat.builder()
                    .seatNo(theaterSeat.getSeatNo())
                    .seatType(theaterSeat.getSeatType())
                    .show(show)
                    .price(theaterSeat.getSeatType()== SeatType.normal?normalSeatPrice:premiumSeatPrice)
                    .build();

            showSeatsList.add(showSeats);
        }

        //showSeatRepository.saveAll(showSeatsList);
        return  showSeatsList;
    }

    public List<String> getShowTime(int movieId, int theaterId) {
        List<String> response=new ArrayList<>();

        List<Show> showList=showRepository.getShowForMovieAndTheater(movieId, theaterId);

        for(Show show:showList){
            String time=show.getShowDate().toString()+" "+show.getShowTime().toString();
            response.add(time);
        }

        return response;
    }


    public ShowResponseDTO getById(int id) {
        Show show=showRepository.findById(id).get();

        ShowResponseDTO showResponseDTO=ShowConvertor.convertEntityToResponseDTO(show);

        return showResponseDTO;
    }

    public List<ShowResponseDTO> getAll() {
        List<ShowResponseDTO> response=new ArrayList<>();

        for(Show show:showRepository.findAll()){
            ShowResponseDTO showResponseDTO=ShowConvertor.convertEntityToResponseDTO(show);
            response.add(showResponseDTO);
        }

        return response;
    }

    public List<ShowSeatResponseDTO> getAvailableSeats(int id) {
        List<ShowSeatResponseDTO> response=new ArrayList<>();

        Show show=showRepository.findById(id).get();

        for(ShowSeat showSeat:show.getListOfShowSeats()){
            if(!showSeat.isBooked()){
                ShowSeatResponseDTO showSeatResponseDTO= ShowSeatConvertor.convertEntityToResponseDTO(showSeat);
                response.add(showSeatResponseDTO);
            }
        }

        return response;
    }


    public Long getSalesForShow(int id) {
        Long sales=0L;

        Show show=showRepository.findById(id).get();
        for(Ticket ticket:show.getListOfTickets()){
            if(!ticket.isCancelled()){
                sales+=ticket.getTotalAmount();
            }
        }

        return sales;
    }

    public List<TicketResponseDTO> getBookedTicketsForShow(int id) {
        List<TicketResponseDTO> response = new ArrayList<>();

        Show show=showRepository.findById(id).get();
        for(Ticket ticket:show.getListOfTickets()){
            if(!ticket.isCancelled()){
                response.add(TicketConvertor.convertEntityToResponseDTO(ticket));
            }
        }

        return response;
    }


    public List<ShowResponseDTO> getShowsForMovieAndLocation(int movieId, String location) {
        List<ShowResponseDTO> response = new ArrayList<>();

        List<Show> showList=showRepository.findShowByMovieId(movieId);

        for(Show show:showList){
            if(show.getTheater().getLocation().equalsIgnoreCase(location)){
                response.add(ShowConvertor.convertEntityToResponseDTO(show));
            }
        }
        return response;
    }


    public List<ShowResponseDTO> getShowsForDate(LocalDate date) {
        List<ShowResponseDTO> response=new ArrayList<>();
        List<Show> showList= showRepository.findByShowDate(date);

        for(Show show:showList){
            response.add(ShowConvertor.convertEntityToResponseDTO(show));
        }

        return response;
    }
}