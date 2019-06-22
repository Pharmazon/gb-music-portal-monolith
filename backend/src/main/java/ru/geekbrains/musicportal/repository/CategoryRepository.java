package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findOneById(Long id);
    Category findOneByTitle(String title);
}
