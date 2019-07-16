package ru.geekbrains.musicportal.entity.artist;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.image.Image;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.entity.track.Genre;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.entity.user.User;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"users", "playlists", "tracks"})
@Table(name = "app_artists")
@EqualsAndHashCode(callSuper = true)
public class Artist extends AbstractEntity {

    @ManyToMany(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "join_users_artists",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> users;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "artist")
    private Collection<Track> tracks;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "artist")
    private Collection<Playlist> playlists;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToMany
    @JoinTable(
            name = "join_artists_genres",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Collection<Genre> genres;
}
