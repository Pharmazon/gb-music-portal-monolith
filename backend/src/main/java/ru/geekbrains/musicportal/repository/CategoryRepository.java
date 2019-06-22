package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.group.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {

    @Query("select c from Category c where c.name = :name")
    Category findOneByName(String name);
}
