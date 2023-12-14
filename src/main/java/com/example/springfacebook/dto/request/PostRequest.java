package com.example.springfacebook.dto.request;

import com.example.springfacebook.entities.Comment;
import com.example.springfacebook.entities.Like;
import com.example.springfacebook.entities.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostRequest {

    private String content;


    private LocalDateTime timeOfPost;


    private User users;

    private List<Comment> comments;


    private List<Like> likes = new ArrayList<>();
}
