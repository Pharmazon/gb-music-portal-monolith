package ru.geekbrains.musicportal.dto.blog;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.marker.CommentViews;

@Data
@NoArgsConstructor
@JsonView(CommentViews.All.class)
@EqualsAndHashCode(callSuper = true)
public class CommentDto extends AbstractDto {

    private Long authorId;
    private String content;
    private String typeCommentedEntity;
    private Long entityId;

    public CommentDto(Comment comment) {
        super.setId(comment.getId());
        super.setName(comment.getName());
        super.setDescription(comment.getDescription());
        super.setCreationDate(comment.getCreationDate());
        super.setLastUpdate(comment.getLastUpdate());
        authorId = comment.getAuthor().getId();
        entityId = comment.getEntityId();
        typeCommentedEntity = comment.getTypeCommentedEntity();
        content = comment.getContent();
    }
}
