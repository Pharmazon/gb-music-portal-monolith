package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.article.ArticleDto;
import ru.geekbrains.musicportal.entity.article.Article;

@Repository
public interface ArticleRepository extends CommonRepository<ArticleDto>,
        CrudRepository<Article, Long> {

    Article findByTitle(String title);
}
