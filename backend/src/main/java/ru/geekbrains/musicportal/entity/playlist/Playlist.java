package ru.geekbrains.musicportal.entity.playlist;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.genre.Genre;
import ru.geekbrains.musicportal.entity.image.Image;
import ru.geekbrains.musicportal.entity.track.PlaylistTrack;
import ru.geekbrains.musicportal.entity.user.User;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"user", "genres", "playlistTracks", "playlistFeatures"})
@Table(name = "app_playlists")
@EqualsAndHashCode(callSuper = true)
public class Playlist extends AbstractEntity {

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "playlist",
            fetch = FetchType.LAZY)
    private Collection<PlaylistFeature> playlistFeatures;

    @OneToMany(
            mappedBy = "playlist",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private Collection<PlaylistTrack> playlistTracks;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToMany
    @JoinTable(
            name = "join_playlists_genres",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Collection<Genre> genres;
}
