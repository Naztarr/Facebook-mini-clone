package com.example.springfacebook.services;

import com.example.springfacebook.dto.response.UserResponse;
import com.example.springfacebook.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserResponse registerUser(User user);
    User getUser(String username, String password);
}
