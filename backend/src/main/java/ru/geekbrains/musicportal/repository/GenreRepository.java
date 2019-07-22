package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.genre.GenreDto;
import ru.geekbrains.musicportal.entity.genre.Genre;

@Repository
public interface GenreRepository extends CommonRepository<GenreDto>, CrudRepository<Genre, Long>,
        JpaSpecificationExecutor<Genre> {

    GenreDto findOneByName(String name);
}
