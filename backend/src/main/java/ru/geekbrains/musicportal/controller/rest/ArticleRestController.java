package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.blog.ArticleDto;
import ru.geekbrains.musicportal.entity.blog.Article;
import ru.geekbrains.musicportal.marker.ArticleViews;
import ru.geekbrains.musicportal.response.ArticleResponse;
import ru.geekbrains.musicportal.response.common.ResponseWrapper;
import ru.geekbrains.musicportal.service.blog.ArticleService;
import ru.geekbrains.musicportal.specification.ArticleSpecs;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/articles")
public class ArticleRestController {

    private final int INITIAL_PAGE = 50;
    private final int PAGE_SIZE = 50;
    private ArticleService articleService;

    @Autowired
    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @JsonView(ArticleViews.All.class)
    @GetMapping
    public ResponseWrapper getAll() {
        Collection<ArticleDto> dtos = articleService.findAllDtos();
        if (dtos != null) {
            return ResponseWrapper.ok(dtos, ArticleResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(ArticleResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArticleViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper getOneById(@PathVariable("id") Long id) {
        ArticleDto dto = articleService.findOneDtoById(id);
        if (dto != null) {
            return ResponseWrapper.ok(dto, ArticleResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(ArticleResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArticleViews.All.class)
    @PutMapping
    public ResponseWrapper update(@Valid ArticleDto dto) {
        Article converted = articleService.convertToEntity(dto);
        Article article = articleService.saveOrUpdate(converted);
        if (converted != null && article != null) {
            return ResponseWrapper.ok(dto, ArticleResponse.SUCCESS_UPDATED);
        }
        return ResponseWrapper.notFound(ArticleResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArticleViews.All.class)
    @GetMapping("filter")
    public ResponseWrapper genrePage(@RequestParam(value = "page") Optional<Integer> page,
                                     @RequestParam(value = "title", required = false) String title,
                                     @RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "description", required = false) String description,
                                     @RequestParam(value = "shortDescription", required = false) String shortDescription,
                                     @RequestParam(value = "content", required = false) String content) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Specification<Article> spec = Specification.where(null);
        if (title != null) spec.and(ArticleSpecs.articleTitleContains(title));
        if (name != null) spec.and(ArticleSpecs.articleNameContains(name));
        if (description != null) spec.and(ArticleSpecs.articleDescriptionContains(description));
        if (shortDescription != null) spec.and(ArticleSpecs.articleShortDescriptionContains(shortDescription));
        if (content != null) spec.and(ArticleSpecs.articleContentContains(content));
        Page<Article> pages = articleService.getArticlesWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        if (pages != null) {
            List<Article> entities = pages.getContent();
            Collection<ArticleDto> dtos = entities.stream()
                    .map(entity -> articleService.convertToDto(entity))
                    .collect(Collectors.toList());
            return ResponseWrapper.ok(dtos, ArticleResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(ArticleResponse.ERROR_NOT_FOUND);
    }
}
