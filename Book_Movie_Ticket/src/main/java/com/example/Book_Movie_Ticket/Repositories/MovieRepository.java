package com.example.Book_Movie_Ticket.Repositories;

import com.example.Book_Movie_Ticket.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
