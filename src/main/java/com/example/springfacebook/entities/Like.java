package com.example.springfacebook.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "users"), nullable = false)
    private User user;
    @ManyToOne()
    @JoinColumn(name = "post_id", referencedColumnName = "post_id",
            foreignKey = @ForeignKey(name = "posts"), nullable = false)
    private Post post;


}
