package ru.geekbrains.musicportal.entity.playlist;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;
import ru.geekbrains.musicportal.entity.track.PlaylistTrack;
import ru.geekbrains.musicportal.entity.user.User;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_playlists")
@EqualsAndHashCode(callSuper = true)
public class Playlist extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "playlist",
            fetch = FetchType.LAZY)
    private Collection<PlaylistFeature> playlistFeatures;

    @OneToMany(
            mappedBy = "playlist",
            fetch = FetchType.LAZY)
    private Collection<PlaylistTrack> playlistTracks;

}