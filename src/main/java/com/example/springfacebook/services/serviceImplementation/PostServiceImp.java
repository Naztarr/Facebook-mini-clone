package com.example.springfacebook.services.serviceImplementation;

import com.example.springfacebook.dto.response.PostResponse;
import com.example.springfacebook.entities.Post;
import com.example.springfacebook.entities.User;
import com.example.springfacebook.repositories.PostRepository;
import com.example.springfacebook.services.PostService;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@Service

public class PostServiceImp implements PostService{

    //Dependency injection of the postRepository interface by constructor method
    private final PostRepository postRepository;

    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createPost(String content, User user) {
        Post post = new Post();
        post.setContent(content);
        post.setUsers(user);
        postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void updatePost(Long id, Post post) {
        Post post1 = postRepository.findById(id).orElseThrow(()-> new IllegalStateException("Post record not found"));
        String postContent = post.getContent();
        if (postContent != null && !postContent.isEmpty() && !Objects.equals(post1.getContent(), postContent)) {
            post1.setContent(postContent);
            postRepository.save(post1);
        }
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);

    }
}


   /* public List<Post> listAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public void addPost(String postBody, Users user) {
        Post post = new Post();
        post.setPostBody(postBody);
        post.setUser(user);
        postRepository.save(post);
    }

    @Override
    public Post getPosts(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public void deleteAPost(Long id) {
        this.postRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updatePost(Long postId, Post post) {
        Post postDB = this.postRepository.findById(postId)
                .orElseThrow(()-> new IllegalStateException("this does not exist"));
        String postBody = post.getPostBody();
        if (postBody != null && postBody.length()>0 && !Objects.equals(postDB.getPostBody(),postBody)){
            postDB.setPostBody(postBody);
            postRepository.save(postDB);
        } */
