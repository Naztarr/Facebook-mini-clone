package com.example.springfacebook.services;

import com.example.springfacebook.entities.Comment;
import lombok.Data;

import java.util.List;
import java.util.Optional;


public interface CommentService {

    void createComment(Comment comment, Long postId);
    Comment getCommentById(Long commentId);
    List<Comment> getAllComment();
    Comment updateCommentById(Long id, Comment comment);
    void deleteCommentById(Long commentId, Long postid);
}
