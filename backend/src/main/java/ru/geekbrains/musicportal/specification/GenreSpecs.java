package ru.geekbrains.musicportal.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.entity.genre.Genre;

public class GenreSpecs {

    public static Specification<Genre> genreNameContains(String word) {
        return (Specification<Genre>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + word + "%");
    }
}
