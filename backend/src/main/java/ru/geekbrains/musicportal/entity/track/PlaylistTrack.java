package ru.geekbrains.musicportal.entity.track;

import lombok.*;
import ru.geekbrains.musicportal.entity.playlist.Playlist;

import javax.persistence.*;

@Data
@Getter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "join_playlists_tracks")
public class PlaylistTrack {

    @EmbeddedId
    private PlaylistTrackKey id;

    @NonNull
    private Integer position;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playlist_id")
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("track_id")
    @JoinColumn(name = "track_id")
    private Track track;

}
