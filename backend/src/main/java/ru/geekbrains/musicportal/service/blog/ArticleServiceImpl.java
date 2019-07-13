package ru.geekbrains.musicportal.service.blog;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.blog.ArticleDto;
import ru.geekbrains.musicportal.entity.blog.Article;
import ru.geekbrains.musicportal.repository.ArticleRepository;

import java.util.Collection;
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
        Optional<Article> optional = findOneEntityById(id);
        if (optional.isPresent()) {
            Article article = optional.get();
            article.setTitle(newTitle);
            saveOrUpdate(article);
        }
    }

    public void addContent(Long id, String content) {
        Optional<Article> optional = findOneEntityById(id);
        if (optional.isPresent()) {
            Article article = optional.get();
            article.setContent(content);
            saveOrUpdate(article);
        }
    }

    @Override
    public Article saveOrUpdate(Article entity) {
        return articleRepository.save(entity);
    }

    @Override
    public Optional<Article> findOneEntityById(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public Collection<ArticleDto> findAllDto() {
        return null;
    }

    @Override
    public ArticleDto findOneDtoById(Long id) {
        return articleRepository.findOneById(id);
    }

    @Override
    public Article convertToEntity(ArticleDto dto) {
        return modelMapper.map(dto, Article.class);
    }
}
