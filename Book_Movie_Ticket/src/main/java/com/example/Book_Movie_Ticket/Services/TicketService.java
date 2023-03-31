package com.example.Book_Movie_Ticket.Services;

import com.example.Book_Movie_Ticket.Converters.TicketConvertor;
import com.example.Book_Movie_Ticket.DTOs.EntryDTOs.TicketEntryDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.TicketResponseDTO;
import com.example.Book_Movie_Ticket.Models.Show;
import com.example.Book_Movie_Ticket.Models.ShowSeat;
import com.example.Book_Movie_Ticket.Models.Ticket;
import com.example.Book_Movie_Ticket.Models.User;
import com.example.Book_Movie_Ticket.Repositories.ShowRepository;
import com.example.Book_Movie_Ticket.Repositories.ShowSeatRepository;
import com.example.Book_Movie_Ticket.Repositories.TicketRepository;
import com.example.Book_Movie_Ticket.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowSeatRepository showSeatRepository;



    public String bookTicket(TicketEntryDTO ticketEntryDTO) throws Exception {

        if(!validateRequestedSeats(ticketEntryDTO)){
            throw new Exception("requested tickets not valid not available/already booked");
        }

        Ticket ticket= TicketConvertor.convertDtoToTicketEntity(ticketEntryDTO);
        ticketRepository.save(ticket);

        int total=0;
        List<Integer> bookingSeatsIds=ticketEntryDTO.getRequestedSeats();
        for(Integer showSeatID:bookingSeatsIds){
            ShowSeat showSeat=showSeatRepository.findById(showSeatID).get();

            total+=showSeat.getPrice();
            showSeat.setTicket(ticket);
            showSeat.setBooked(true);
            showSeat.setBookedOn(new Date());
            ticket.getBookedSeatList().add(showSeat);
            showSeatRepository.save(showSeat);
        }

        ticket.setTotalAmount(total);

        Show show=showRepository.findById(ticketEntryDTO.getShowId()).get();
        ticket.setShow(show);
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setMovieName(show.getMovie().getName());
        ticket.setTheaterName(show.getTheater().getName());

        show.getListOfTickets().add(ticket);

        User user=userRepository.findById(ticketEntryDTO.getUserId()).get();

        ticket.setUser(user);
        user.getListOfTickets().add(ticket);

        userRepository.save(user);
        showRepository.save(show);

        return "ticket booked successfully. Ticket Id:"+ticket.getId();
    }

    public boolean validateRequestedSeats(TicketEntryDTO ticketEntryDTO){

        List<Integer> requestedSeats=ticketEntryDTO.getRequestedSeats();

        for(Integer showSeatId:requestedSeats){
            ShowSeat showSeat = showSeatRepository.findById(showSeatId).get();
            if(showSeat.isBooked()){
                return false;
            }
        }

        return true;
    }

    public String cancelTicket(int ticketId) {

        Ticket ticket=ticketRepository.findById(ticketId).get();

        for (ShowSeat showSeat:ticket.getBookedSeatList()){
            showSeat.setBooked(false);
            showSeat.setBookedOn(null);
            showSeat.setTicket(null);
            showSeatRepository.save(showSeat);
        }

        ticket.setCancelled(true);
        ticket.getBookedSeatList().clear();

        ticketRepository.save(ticket);

        return "ticket cancelled";
    }

    public TicketResponseDTO getById(int id) {
        Ticket ticket=ticketRepository.findById(id).get();

        return TicketConvertor.convertEntityToResponseDTO(ticket);
    }


    public List<TicketResponseDTO> getAll() {
        List<TicketResponseDTO> responseDTOList=new ArrayList<>();

        for(Ticket ticket: ticketRepository.findAll()){
            TicketResponseDTO ticketResponseDTO=TicketConvertor.convertEntityToResponseDTO(ticket);
            responseDTOList.add(ticketResponseDTO);
        }

        return responseDTOList;
    }
}