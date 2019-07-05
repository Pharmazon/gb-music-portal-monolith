package ru.geekbrains.musicportal.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.entity.user.User;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentDto extends AbstractDto {

    private User author;
    private String content;
    private String entity;
    private Long entityId;

    public CommentDto(Comment comment) {
        super.setId(comment.getId());
        author = comment.getAuthor();
        content = comment.getContent();
        entityId = comment.getEntityId();
        entity = comment.getTypeCommentedEntity();
    }
}
