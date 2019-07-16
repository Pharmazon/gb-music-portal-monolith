package ru.geekbrains.musicportal.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.entity.artist.Artist;

public class ArtistSpecs {

    public static Specification<Artist> artistNameContains(String word) {
        return (Specification<Artist>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + word + "%");
    }
}
