package com.example.springfacebook.controllers;

import com.example.springfacebook.entities.Comment;
import com.example.springfacebook.repositories.CommentRepository;
import com.example.springfacebook.services.CommentService;
import lombok.Data;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Data
public class CommentController {

    private final CommentService commentService;


    private final CommentRepository commentRepository;

    public CommentController(CommentService commentServiceImplementation, CommentRepository commentRepository) {
        this.commentService = commentServiceImplementation;
        this.commentRepository = commentRepository;
    }

    @PostMapping("/comment/{id}")
    public String addComment(@ModelAttribute("comment") Comment comment, @PathVariable(name = "id") Long postId) {
        commentService.createComment(comment, postId);
        return "redirect:/newsfeed";
    }
    @GetMapping("/update/{id}")
    public String editComment(@PathVariable(name = "id") Long commentId, Model model) {
        Comment comment = commentService.getCommentById(commentId);
        model.addAttribute("comment", comment);
        return "editComment";
    }
    @PostMapping("/update")
    public String editComment(@ModelAttribute("comments") Comment editedComment) {
        Comment comment = commentRepository.findById(editedComment.getId()).get();
        comment.setContent(editedComment.getContent());
        commentRepository.save(editedComment);
        return "redirect:/home";
    }
    @GetMapping("/deleteComment/{id}/{postID}")
    public String deleteComment(@PathVariable(name = "id") Long commentId, @PathVariable(name = "postID") Long postId) {
        commentService.deleteCommentById(commentId, postId);
        return "redirect:/home";
    }
}
