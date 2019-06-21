package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    Article findByTitle(String  title);
}