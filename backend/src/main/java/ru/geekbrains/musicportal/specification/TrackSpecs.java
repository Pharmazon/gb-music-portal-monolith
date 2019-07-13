package ru.geekbrains.musicportal.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.musicportal.entity.track.Track;

public class TrackSpecs {

    public static Specification<Track> bandIdEquals(Long bandId) {
        return (Specification<Track>) (root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("band_id"), bandId);
    }

    public static Specification<Track> playlistIdEquals(Long playlistId) {
        return (Specification<Track>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("playlist_id"), playlistId);
    }

}
