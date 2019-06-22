package ru.geekbrains.musicportal.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.entity.group.Category;
import ru.geekbrains.musicportal.repository.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getById(String id){
        return categoryRepository.findById(id);
    }

    public Category getByTitle(String title){
        return categoryRepository.findOneByName(title);
    }

    @Override
    public Category save(Category entity) {
        return categoryRepository.save(entity);
    }

    @Override
    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id);
    }
}
