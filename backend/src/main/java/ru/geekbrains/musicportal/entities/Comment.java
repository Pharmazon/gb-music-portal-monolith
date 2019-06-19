package ru.geekbrains.musicportal.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "user_id")
    @ManyToMany (mappedBy = "users")
    private Long userId;

    @Column(name = "comment_content")
    private String commentContent;
}
