package ru.geekbrains.musicportal.entity.playlist;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.entity.user.User;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_playlist")
@EqualsAndHashCode(callSuper = true)
public class Playlist extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "join_playlist_track",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id"))
    private Collection<Track> tracks;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "playlist")
    private Collection<PlaylistFeature> playlistFeatures;

}
