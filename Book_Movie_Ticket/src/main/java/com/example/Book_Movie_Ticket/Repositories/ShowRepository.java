package com.example.Book_Movie_Ticket.Repositories;

import com.example.Book_Movie_Ticket.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowRepository  extends JpaRepository<Show,Integer> {
    @Query(value = "select * from shows s where s.movie_id=:movieId and s.theater_id=:theaterId"
            , nativeQuery = true)
    List<Show> getShowForMovieAndTheater(int movieId, int theaterId);

    @Query(value = "select * from shows s where s.movie_id=:movieId"
            , nativeQuery = true)
    List<Show> findShowByMovieId(int movieId);

    List<Show> findByShowDate(LocalDate date);
}
