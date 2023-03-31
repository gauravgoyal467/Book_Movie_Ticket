package com.example.Book_Movie_Ticket.Converters;

import com.example.Book_Movie_Ticket.DTOs.EntryDTOs.UserEntryDTO;
import com.example.Book_Movie_Ticket.DTOs.ResponseDTOs.UserResponseDTO;
import com.example.Book_Movie_Ticket.Models.User;

public class UserConvertor {

    public static User ConvertDtoToUserEntity(UserEntryDTO userEntryDTO){
        User user=User.builder()
                .name(userEntryDTO.getName())
                .age(userEntryDTO.getAge())
                .email(userEntryDTO.getEmail())
                .mobile(userEntryDTO.getMobile())
                .build();

        return user;
    }

    public static UserResponseDTO convertToUserResponseDTO(User user){
        UserResponseDTO userResponseDTO= UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .age(user.getAge())
                .email(user.getEmail())
                .mobile(user.getMobile())
                .build();

        return userResponseDTO;
    }

}
