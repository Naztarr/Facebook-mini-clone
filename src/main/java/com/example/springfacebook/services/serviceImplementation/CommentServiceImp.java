package com.example.springfacebook.services.serviceImplementation;

import com.example.springfacebook.entities.Comment;
import com.example.springfacebook.entities.Post;
import com.example.springfacebook.repositories.CommentRepository;
import com.example.springfacebook.repositories.PostRepository;
import com.example.springfacebook.services.CommentService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@Service
public class CommentServiceImp implements CommentService {



    //Dependency injection of the postRepository interface by constructor method
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImp(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public void createComment(Comment comment, Long postId) {

        Comment savedComment = commentRepository.save(comment);
        Optional<Post> post = postRepository.findById(postId);
        List<Comment> comments = post.get().getComments();
        comments.add(savedComment);
        post.get().setComments(comments);
        postRepository.save(post.get());
    }

    @Override
    public Comment getCommentById(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        return comment.get();
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Comment updateCommentById(Long id, Comment comment) {
        Comment comment1 = commentRepository.findById(id).orElseThrow(()->new IllegalStateException("Comment record not found"));
        String commentContent = comment.getContent();
        if (commentContent != null && !commentContent.isEmpty() && !Objects.equals(commentContent, String.valueOf(comment1))) {
            comment1.setContent(commentContent);
            return commentRepository.save(comment1);
        }
        return null;
    }

    @Override
    public void deleteCommentById(Long commentId, Long postId) {
        Post post = postRepository.findById(postId).get();
        Comment comment = commentRepository.findById(commentId).get();
        post.getComments().remove(comment);
        commentRepository.deleteById(commentId);

    }
}

//    public Iterable<Comment> getAllComment() {
//        return commentRepository.findAll();
//    }
//    public void saveComment(Comment comment, Long postId) {
//        Comment commentDB = commentRepository.save(comment);
//        Optional<Post> post = postRepository.findById(postId);
//        List<Comment> comments = post.get().getComments();
//        comments.add(commentDB);
//        post.get().setComments(comments);
//        postRepository.save(post.get());
//    }
//    public Comment getCommentById(Long commentId) {
//        Optional<Comment> comment = commentRepository.findById(commentId);
//        return comment.get();
//    }
//    public void updateComment(Comment editedComment) {
//        commentRepository.save(editedComment);
//    }
//    public void deleteComment(Long commentId, Long postID) {
//        Post post =  postRepository.findById(postID).get();
//        Comment comment = commentRepository.findById(commentId).get();
//        post.getComments().remove(comment);
//        commentRepository.deleteById(commentId);
//    }
