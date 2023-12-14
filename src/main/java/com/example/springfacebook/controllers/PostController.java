package com.example.springfacebook.controllers;

import com.example.springfacebook.entities.Comment;
import com.example.springfacebook.entities.Like;
import com.example.springfacebook.entities.Post;
import com.example.springfacebook.entities.User;
import com.example.springfacebook.repositories.CommentRepository;
import com.example.springfacebook.services.CommentService;
import com.example.springfacebook.services.LikeService;
import com.example.springfacebook.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Data
@Controller
public class PostController {

    private final PostService postService;

    private final CommentService commentService;

    private final LikeService likeService;
    private final CommentRepository commentRepository;

    public PostController(PostService postService, CommentService commentService, LikeService likeService, CommentRepository commentRepository) {
        this.postService = postService;
        this.commentService = commentService;
        this.likeService = likeService;
        this.commentRepository = commentRepository;
    }


   @GetMapping("/post")
    public String viewPostPage(Model model){
        //create a list from the db
        Post post = new Post();
        List<Post> listPosts = postService.getAllPosts();
        Iterable<Comment> comments = commentService.getAllComment();
//        int total = listPosts.size();
        //set the post list to modal
        model.addAttribute("listPosts", listPosts);
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", comments);
        model.addAttribute("newPost",post);
//        model.addAttribute("totalLikesOnPost", total);
        model.addAttribute("newPostLike", new Like());

        //return the page
        return "home";
    }

    @PostMapping("/saveNewPost")
    public String savePost(@ModelAttribute("newPost") Post newPost, HttpServletRequest request){
        User loggedInUser = (User) request.getSession().getAttribute("user_session");
        postService.createPost(newPost.getContent(), loggedInUser);
        return "redirect:/home";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteAPost(@PathVariable(name = "id")Long id, HttpServletRequest request ){
        User loggedInUser = (User) request.getSession().getAttribute("user_session");
        Post post = postService.getPostById(id);
        if (Objects.equals(loggedInUser.getId(), post.getUsers().getId())) {
            postService.deletePostById(id);
            return "redirect:/home";
        }
        return "redirect:/home";
    }

//    @GetMapping("/home")
//    public String showHome(@PathVariable(name = "id")Long id, Model model, HttpServletRequest request) {
//        Post post = new Post();
//        model.addAttribute("newPost",post);
//        return "home";
//    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable(name = "id")Long id, Model model, HttpServletRequest request) {
        User loggedInUser = (User) request.getSession().getAttribute("user_session");
        Post post = postService.getPostById(id);
        if (Objects.equals(loggedInUser.getId(), post.getUsers().getId())) {
            model.addAttribute("newPost", post);
            //temp
            return "edit";
        }
        return "redirect:/home";
    }

    @PostMapping("/edit_post")
    public String editPost(@ModelAttribute("newPost")Post post){
        postService.updatePost(post.getId(), post);
        return "redirect:/home";
    }


    @PostMapping("/like/{id}")
    public String likeIndex(@PathVariable("id") Long id, HttpSession session, Model model) {
        User userObj = (User) session.getAttribute("user_session");
        Post post = postService.getPostById(id);
        Like newPostLike = likeService.getPostLikeByPostAndUser(post,userObj);

        model.addAttribute("newPostLike", newPostLike);
        return "redirect:/home";
    }
    @PostMapping("/comment/{id}")
    public String addComment(@ModelAttribute("comment") Comment comment, @PathVariable(name = "id") Long postId) {
        commentService.createComment(comment, postId);
        return "redirect:/home";
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
