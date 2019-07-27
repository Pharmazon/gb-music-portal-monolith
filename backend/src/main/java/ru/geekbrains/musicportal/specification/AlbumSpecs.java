package ru.geekbrains.musicportal.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.entity.album.Album;

public class AlbumSpecs {

    public static Specification<Album> playlistNameContains(String word) {
        return (Specification<Album>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + word + "%");
    }

    public static Specification<Album> artistIdEquals(Long artistId) {
        return (Specification<Album>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("arist_id"), artistId);
    }
}
