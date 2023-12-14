package com.example.springfacebook.repositories;

import com.example.springfacebook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//we use the repository to interact with our database tables
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);
    User findByUserNameAndPassWord(String username, String password);
}
