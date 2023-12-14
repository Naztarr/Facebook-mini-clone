package com.example.springfacebook.dto.request;

import com.example.springfacebook.Enums.Gender;
import com.example.springfacebook.entities.Post;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserRequest {



    private String firstName;


    private String lastName;


    private String userName;



}
