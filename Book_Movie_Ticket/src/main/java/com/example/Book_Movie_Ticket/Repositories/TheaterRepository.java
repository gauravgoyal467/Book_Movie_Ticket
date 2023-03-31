package com.example.Book_Movie_Ticket.Repositories;

import com.example.Book_Movie_Ticket.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository  extends JpaRepository<Theater,Integer> {
    public List<Theater> findByLocation(String location);
}
