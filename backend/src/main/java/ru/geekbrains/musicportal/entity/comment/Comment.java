package ru.geekbrains.musicportal.entity.comment;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.user.User;

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
    @JoinColumn(name = "author_id")
    private User author;

    @Column (name = "entity")
    private String typeCommentedEntity;

    @Column (name = "entity_id")
    private Long entityId;

}
