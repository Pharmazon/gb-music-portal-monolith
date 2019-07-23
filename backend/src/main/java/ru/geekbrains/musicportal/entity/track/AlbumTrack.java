package ru.geekbrains.musicportal.entity.track;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.album.Album;

import javax.persistence.*;

@Data
@Getter
@Entity
@NoArgsConstructor
@Table(name = "join_albums_tracks")
public class AlbumTrack {

    @EmbeddedId
    private AlbumTrackKey id;

    private int position;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("album_id")
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("track_id")
    @JoinColumn(name = "track_id")
    private Track track;
}
