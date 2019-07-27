package ru.geekbrains.musicportal.dto.article;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.article.Article;
import ru.geekbrains.musicportal.marker.ArticleViews;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArticleDto extends AbstractDto {

    @JsonView(ArticleViews.All.class)
    private String shortDescripton;

    @JsonView(ArticleViews.All.class)
    private String content;

    public ArticleDto(Article entity) {
        if (entity == null) return;
        super.setId(entity.getId());
        super.setName(entity.getName());
        super.setCreationDate(entity.getCreationDate());
        super.setLastUpdate(entity.getLastUpdate());
        super.setDescription(entity.getDescription());
        this.shortDescripton = entity.getShortDescripton();
        this.content = entity.getContent();
    }
}
