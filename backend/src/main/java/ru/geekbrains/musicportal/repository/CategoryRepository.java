package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.group.CategoryDto;
import ru.geekbrains.musicportal.entity.group.Category;

@Repository
public interface CategoryRepository extends CommonRepository<CategoryDto>,
        CrudRepository<Category, Long> {

    Category findOneByName(String name);
}
