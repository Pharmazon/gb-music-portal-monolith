package ru.geekbrains.musicportal.repository;

import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.database.Category;

@Repository
public interface CategoryRepository {
    Category findOneById(Long id);
    Category findOneByTitle(String title);
}
