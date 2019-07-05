package ru.geekbrains.musicportal.service.blog;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.blog.ArticleDto;
import ru.geekbrains.musicportal.entity.blog.Article;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.repository.ArticleRepository;

import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository,
                              ModelMapper modelMapper) {
        this.articleRepository = articleRepository;
        this.modelMapper = modelMapper;
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    public void changeTitleById(Long id, String newTitle) {
        Optional<Article> optional = findById(id);
        if (optional.isPresent()) {
            Article article = optional.get();
            article.setTitle(newTitle);
            save(article);
        }
    }

    public void addContent(Long id, String content) {
        Optional<Article> optional = findById(id);
        if (optional.isPresent()) {
            Article article = optional.get();
            article.setContent(content);
            save(article);
        }
    }

    @Override
    public Article save(Article entity) {
        return articleRepository.save(entity);
    }

    @Override
    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public Iterable<Comment> findAll() {
        return null;
    }

    @Override
    public Article convertToEntity(ArticleDto dto) {
        return modelMapper.map(dto, Article.class);
    }
}
