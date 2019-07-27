package ru.geekbrains.musicportal.service.article;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.article.ArticleDto;
import ru.geekbrains.musicportal.entity.article.Article;
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

    public boolean deleteById(Long id) {
        articleRepository.deleteById(id);
        return true;
    }

    public boolean changeTitleById(Long id, String newTitle) {
        Optional<Article> optional = findOneEntityById(id);
        if (optional.isPresent()) {
            Article article = optional.get();
            article.setTitle(newTitle);
            saveOrUpdate(article);
            return true;
        }
        return false;
    }

    public boolean addContent(Long id, String content) {
        Optional<Article> optional = findOneEntityById(id);
        if (optional.isPresent()) {
            Article article = optional.get();
            article.setContent(content);
            saveOrUpdate(article);
            return true;
        }
        return false;
    }

    @Override
    public Page<Article> getArticlesWithPagingAndFiltering(int pageNumber, int pageSize, Specification<Article> specification) {
        return null;
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
    public Collection<ArticleDto> findAllDtos() {
        return articleRepository.findAllByIdNotNull();
    }

    @Override
    public Collection<Article> findAll() {
        return (Collection<Article>) articleRepository.findAll();
    }

    @Override
    public ArticleDto findOneDtoById(Long id) {
        return articleRepository.findOneById(id);
    }

    @Override
    public Article convertToEntity(ArticleDto dto) {
        return modelMapper.map(dto, Article.class);
    }

    @Override
    public ArticleDto convertToDto(Article entity) {
        return modelMapper.map(entity, ArticleDto.class);
    }
}
