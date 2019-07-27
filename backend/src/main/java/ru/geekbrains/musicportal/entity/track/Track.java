package ru.geekbrains.musicportal.entity.track;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.geekbrains.musicportal.entity.artist.Artist;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.genre.Genre;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_tracks")
@ToString(exclude = {"genres", "trackFeatures" , "playlistTracks"})
@EqualsAndHashCode(callSuper = true)
public class Track extends AbstractEntity {

    @Column(name = "link")
    private String fileLink;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

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
