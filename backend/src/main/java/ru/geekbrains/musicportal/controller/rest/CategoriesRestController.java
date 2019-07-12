package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.musicportal.dto.group.CategoryDto;
import ru.geekbrains.musicportal.dto.marker.CategoryViews;
import ru.geekbrains.musicportal.service.category.CategoryService;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/api/miraculous/genres")
public class CategoriesRestController {
    private CategoryService categoryService;

    @Autowired
    public CategoriesRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Выдать список всех жанров (категорий)
     */
    @GetMapping
    @JsonView(CategoryViews.List.class)
    public Collection<CategoryDto> getAllGenres(){
        return categoryService.findAllDto();
    }
}
