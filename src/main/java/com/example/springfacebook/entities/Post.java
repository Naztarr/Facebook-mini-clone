package com.example.springfacebook.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "time_of_post")
    private LocalDateTime timeOfPost;

    @ManyToOne()
    @JoinColumn(name = "user_post", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "users"), nullable = false)
    private User users;

    @Column(name = "comments")
    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments;

    @Column(name = "likes")
    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private Set<Like> likes;
}

