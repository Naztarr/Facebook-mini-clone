package com.example.springfacebook.dto.response;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;


    private String firstName;


    private String lastName;


    private String userName;

}
