package ru.geekbrains.musicportal.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.entity.track.Track;

public class TrackSpecs {

    public static Specification<Track> artistIdEquals(Long artistId) {
        return (Specification<Track>) (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("artist_id"), artistId);
    }

    public static Specification<Track> playlistIdEquals(Long playlistId) {
        return (Specification<Track>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("playlist_id"), playlistId);
    }

    public static Specification<Track> trackNameContains(String word) {
        return (Specification<Track>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + word + "%");
    }

}
