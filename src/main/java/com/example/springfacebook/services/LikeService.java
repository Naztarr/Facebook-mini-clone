package com.example.springfacebook.services;

import com.example.springfacebook.entities.Like;
import com.example.springfacebook.entities.Post;
import com.example.springfacebook.entities.User;

public interface LikeService {

    public Like getPostLikeByPostAndUser(Post post, User user);

    public void addLike(Like like);

    public void deleteLike(Like like);

    public void deleteAllPostLike(Post post, User user);

    public int totalNumberOfLikes(Long id) ;
}



