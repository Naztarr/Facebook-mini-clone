package com.example.springfacebook.dto.response;

import com.example.springfacebook.entities.Comment;
import com.example.springfacebook.entities.Like;
import com.example.springfacebook.entities.User;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PostResponse {

    private Long id;

    private String content;


    private LocalDateTime timeOfPost;

    private User users;

    private List<Comment> comments;

    private List<Like> likes = new ArrayList<>();
}
