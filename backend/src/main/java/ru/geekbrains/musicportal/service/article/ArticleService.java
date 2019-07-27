package ru.geekbrains.musicportal.service.article;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.dto.article.ArticleDto;
import ru.geekbrains.musicportal.entity.article.Article;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface ArticleService extends CommonService<Article, ArticleDto> {

    boolean changeTitleById(Long id, String newTitle);

    boolean addContent(Long id, String content);

    Page<Article> getArticlesWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Article> specification);
}
