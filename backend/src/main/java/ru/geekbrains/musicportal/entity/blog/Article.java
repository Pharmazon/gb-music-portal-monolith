package ru.geekbrains.musicportal.entity.blog;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.Comment;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.user.User;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_articles")
@EqualsAndHashCode(callSuper = true)
public class Article extends AbstractEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "short_description")
    private String shortDescripton;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

}
