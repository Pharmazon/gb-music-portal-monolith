package ru.geekbrains.musicportal.entity.band;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.image.Image;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.entity.user.User;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"users", "playlists", "tracks"})
@Table(name = "app_bands")
@EqualsAndHashCode(callSuper = true)
public class Band extends AbstractEntity {

    @ManyToMany(
            cascade = CascadeType.DETACH,
            fetch = FetchType.EAGER)
    @JoinTable(
            name = "join_user_bands",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> users;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "band")
    private Collection<Track> tracks;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "band")
    private Collection<Playlist> playlists;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;
}
