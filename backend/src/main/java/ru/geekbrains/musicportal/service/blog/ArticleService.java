package ru.geekbrains.musicportal.service.blog;

import ru.geekbrains.musicportal.dto.blog.ArticleDto;
import ru.geekbrains.musicportal.entity.blog.Article;
import ru.geekbrains.musicportal.service.common.CommonService;

public interface ArticleService extends CommonService<Article, ArticleDto> {

    void deleteById(Long id);

    void changeTitleById(Long id, String newTitle);

    void addContent(Long id, String content);

}
