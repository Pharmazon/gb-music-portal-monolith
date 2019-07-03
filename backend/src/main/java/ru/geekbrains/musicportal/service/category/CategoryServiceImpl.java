package ru.geekbrains.musicportal.service.category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.group.CategoryDto;
import ru.geekbrains.musicportal.entity.group.Category;
import ru.geekbrains.musicportal.repository.CategoryRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<Category> getById(Long id){
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
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Collection<CategoryDto> findAll() {
        return null;
    }

    @Override
    public Category convertToEntity(CategoryDto dto) {
        return modelMapper.map(dto, Category.class);
    }
}
