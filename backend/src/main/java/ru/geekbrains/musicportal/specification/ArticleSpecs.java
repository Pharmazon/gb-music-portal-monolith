package ru.geekbrains.musicportal.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.entity.blog.Article;

public class ArticleSpecs {

    public static Specification<Article> articleDescriptionContains(String word) {
        return (Specification<Article>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("description"), "%" + word + "%");
    }

    public static Specification<Article> articleNameContains(String word) {
        return (Specification<Article>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + word + "%");
    }

    public static Specification<Article> articleContentContains(String word) {
        return (Specification<Article>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("content"), "%" + word + "%");
    }

    public static Specification<Article> articleTitleContains(String word) {
        return (Specification<Article>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Article> articleShortDescriptionContains(String word) {
        return (Specification<Article>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("shortDescription"), "%" + word + "%");
    }
}
