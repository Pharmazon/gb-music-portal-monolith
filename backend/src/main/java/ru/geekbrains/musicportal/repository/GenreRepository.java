package ru.geekbrains.musicportal.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.track.GenreDto;
import ru.geekbrains.musicportal.entity.track.Genre;

@Repository
public interface GenreRepository extends CommonRepository<GenreDto>, CrudRepository<Genre, Long>,
        JpaSpecificationExecutor<Genre> {

    GenreDto findOneByName(String name);
}
