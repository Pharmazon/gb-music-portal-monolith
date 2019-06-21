package ru.geekbrains.musicportal.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "articles")
@Data
public class Article {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "short_description")
    private String shortDescripton;

    @Column(name = "article_content")
    private String articleContent;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "article_comments",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private Collection<Comment> comments;


}
