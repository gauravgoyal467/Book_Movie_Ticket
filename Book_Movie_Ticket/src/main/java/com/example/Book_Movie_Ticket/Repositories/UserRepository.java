package com.example.Book_Movie_Ticket.Repositories;

import com.example.Book_Movie_Ticket.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User,Integer> {
}
