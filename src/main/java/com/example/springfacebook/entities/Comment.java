package com.example.springfacebook.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "time_of_comment")
    private LocalDateTime timeOfComment;

    @ManyToOne()
    @JoinColumn(name = "user_comments", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "users"), nullable = false)
    private User user;


    @ManyToOne
    @JoinColumn(name = "post", referencedColumnName = "post_id", nullable = false)
    private Post post;


}
