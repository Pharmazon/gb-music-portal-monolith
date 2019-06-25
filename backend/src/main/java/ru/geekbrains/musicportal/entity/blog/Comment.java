package ru.geekbrains.musicportal.entity.blog;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_comments")
@EqualsAndHashCode(callSuper = true)
public class Comment extends AbstractEntity {

    @Column(name = "comment_content")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private Article article;

}
