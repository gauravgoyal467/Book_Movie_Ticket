package com.example.Book_Movie_Ticket.Repositories;

import com.example.Book_Movie_Ticket.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository  extends JpaRepository<Ticket,Integer> {
    public Ticket findByTicketId(String ticketId);
}
