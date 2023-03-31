package com.example.Book_Movie_Ticket.Repositories;

import com.example.Book_Movie_Ticket.Models.TheaterSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository  extends JpaRepository<TheaterSeat,Integer> {
}
