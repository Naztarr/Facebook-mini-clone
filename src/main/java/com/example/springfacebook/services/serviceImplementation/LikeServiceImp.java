package com.example.springfacebook.services.serviceImplementation;


import com.example.springfacebook.entities.Like;
import com.example.springfacebook.entities.Post;
import com.example.springfacebook.entities.User;
import com.example.springfacebook.repositories.LikeRepository;
import com.example.springfacebook.services.LikeService;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Service
@Transactional
public class LikeServiceImp implements LikeService {

    private final LikeRepository likeRepository;


    public LikeServiceImp(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public Like getPostLikeByPostAndUser(Post post, User user) {
        return likeRepository.getPostLikeByPostAndUser(post, user);
    }

    @Override

    public void addLike(Like like) {
        likeRepository.save(like);
    }

    @Override
    public void deleteLike(Like like) {
        likeRepository.delete(like);
    }

    @Override
    public void deleteAllPostLike(Post post, User user) {
        likeRepository.deleteAllByPostAndUser(post, user);
    }

    @Override
    public int totalNumberOfLikes(Long id) {
        return likeRepository.countLikeByPostId(id);
    }

}

//    public Like getPostLikeByPostAndUser(Post post, Users user) {
//        return likeRepository.getPostLikeByPostAndAndUser(post, user);
//    }
//
//    public void addLike(Like like) {
//        likeRepository.save(like);
//    }
//
//    public void deleteLike(Like like) {
//        likeRepository.delete(like);
//    }
//
//    public void deleteAllPostLike(Post post, Users user) {
//        likeRepository.deleteAllByPostAndUser(post, user);
//    }
//
//    @Override
//    public int totalNumberOfLikes(Long id) {
//        return likeRepository.countLikeByPostId(id);
//    }
//
