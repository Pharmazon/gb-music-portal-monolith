package ru.geekbrains.musicportal.entity.track;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.band.Band;
import ru.geekbrains.musicportal.entity.playlist.Playlist;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "join_tracks_genres",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id"))
    private Collection<Track> tracks;

    @ManyToMany
    @JoinTable(
            name = "join_playlists_genres",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private Collection<Playlist> playlists;

    @ManyToMany
    @JoinTable(
            name = "join_bands_genres",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id"))
    private Collection<Band> bands;
}
