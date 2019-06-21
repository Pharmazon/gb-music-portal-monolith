package ru.geekbrains.musicportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.entity.Category;
import ru.geekbrains.musicportal.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getById(Long id){
        return categoryRepository.findOneById(id);
    }

    public Category getByTitle(String title){
        return categoryRepository.findOneByTitle(title);
    }

    public Category saveCategory(Category category){
        Category categoryResult = categoryRepository.save(category);
        return categoryResult;
    }

    public void deleteCategory(Category category){
        categoryRepository.delete(category);
    }
}
