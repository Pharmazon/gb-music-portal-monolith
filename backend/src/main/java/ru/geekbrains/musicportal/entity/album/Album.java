package ru.geekbrains.musicportal.entity.album;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.geekbrains.musicportal.entity.artist.Artist;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.genre.Genre;
import ru.geekbrains.musicportal.entity.image.Image;
import ru.geekbrains.musicportal.entity.track.AlbumTrack;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_albums")
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"artist", "genres", "albumTracks", "albumFeatures"})
public class Album extends AbstractEntity {

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(
            mappedBy = "album",
            fetch = FetchType.LAZY)
    private Collection<AlbumFeature> albumFeatures;

    @OneToMany(
            mappedBy = "album",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Collection<AlbumTrack> albumTracks;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToMany
    @JoinTable(
            name = "join_albums_genres",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Collection<Genre> genres;
}
