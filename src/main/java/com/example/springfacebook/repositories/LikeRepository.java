package com.example.springfacebook.repositories;

import com.example.springfacebook.entities.Like;
import com.example.springfacebook.entities.Post;
import com.example.springfacebook.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Like getPostLikeByPostAndUser(Post post, User user);
    int countLikeByPostId(Long id);
    void deleteAllByPostAndUser(Post post, User user);

}
