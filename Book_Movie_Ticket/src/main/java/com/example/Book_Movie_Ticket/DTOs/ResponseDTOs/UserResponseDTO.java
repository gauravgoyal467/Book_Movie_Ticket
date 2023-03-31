package com.example.Book_Movie_Ticket.DTOs.ResponseDTOs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {
    private int id;
    private String name;
    private String email;
    private int age;
    private String mobile;
}