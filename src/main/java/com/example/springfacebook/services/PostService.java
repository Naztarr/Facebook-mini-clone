package com.example.springfacebook.services;

import com.example.springfacebook.entities.Post;
import com.example.springfacebook.entities.User;

import java.util.List;
import java.util.Optional;

public interface PostService {

    void createPost(String content, User user);
    Post getPostById(Long id);
    List<Post> getAllPosts();
    void updatePost(Long id, Post post);
    void deletePostById(Long id);
}
//    List<Post> listAllPosts();
//    void addPost(String body, Users user);
//    Post getPosts(Long id);
//    void deleteAPost(Long id);
//    void updatePost(Long postId, Post post);