package ru.geekbrains.musicportal.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.entity.Article;
import ru.geekbrains.musicportal.repository.ArticleRepository;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void saveArticle(Article article) {
        articleRepository.save(article);
    }

    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }

    public void changeTitle(String oldTitle, String newTitle) {
        Article article = articleRepository.findByTitle(oldTitle);
        article.setTitle(newTitle);
    }

    public void addContent(String title, String content) {
        Article article = articleRepository.findByTitle(title);
        article.setArticleContent(content);
    }
}
