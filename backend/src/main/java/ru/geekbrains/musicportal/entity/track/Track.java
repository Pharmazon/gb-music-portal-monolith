package ru.geekbrains.musicportal.entity.track;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.band.Band;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_tracks")
@EqualsAndHashCode(callSuper = true)
public class Track extends AbstractEntity {

    @Column(name = "link")
    private String fileLink;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "band_id")
    private Band band;

    @ManyToMany
    @JoinTable(
            name = "join_tracks_genres",
            joinColumns = @JoinColumn(name = "track_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Collection<Genre> genres;

    @OneToMany(
            mappedBy = "track",
            fetch = FetchType.LAZY)
    private Collection<TrackFeature> trackFeatures;

    @OneToMany(
            mappedBy = "track",
            fetch = FetchType.LAZY)
    private Collection<PlaylistTrack> playlistTracks;

}
