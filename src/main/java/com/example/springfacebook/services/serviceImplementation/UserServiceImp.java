package com.example.springfacebook.services.serviceImplementation;

import com.example.springfacebook.dto.request.UserRequest;
import com.example.springfacebook.dto.response.UserResponse;
import com.example.springfacebook.entities.User;
import com.example.springfacebook.repositories.UserRepository;
import com.example.springfacebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserServiceImp implements UserService {



    private final UserRepository userRepository;


@Autowired
    public UserServiceImp(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public UserResponse registerUser(User user){
        Optional<User> userOpt = userRepository.findByUserName(user.getUserName());
        if (userOpt.isPresent()){
            throw new IllegalStateException("username already exists");
        }
        User savedUser = userRepository.save(user);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(savedUser.getId());
        userResponse.setFirstName(savedUser.getFirstName());
        userResponse.setLastName(savedUser.getLastName());
        userResponse.setUserName(savedUser.getUserName());
        return userResponse;

    }

    public User getUser(String username, String password){
        User user = userRepository.findByUserNameAndPassWord(username, password);
        return user;
    }
}
