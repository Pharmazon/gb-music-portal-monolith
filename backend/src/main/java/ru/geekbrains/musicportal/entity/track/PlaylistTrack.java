package ru.geekbrains.musicportal.entity.track;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.playlist.Playlist;

import javax.persistence.*;

@Data
@Getter
@Entity
@NoArgsConstructor
@Table(name = "join_playlists_tracks")
public class PlaylistTrack {

    @EmbeddedId
    private PlaylistTrackKey id;

    private int position;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playlist_id")
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("track_id")
    @JoinColumn(name = "track_id")
    private Track track;

}
