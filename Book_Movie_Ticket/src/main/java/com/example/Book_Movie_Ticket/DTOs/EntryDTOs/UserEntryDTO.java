package com.example.Book_Movie_Ticket.DTOs.EntryDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntryDTO {

    private String name;

    private int age;

    private String email;

    private String mobile;
}
