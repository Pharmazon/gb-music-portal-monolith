package ru.geekbrains.musicportal.entity.track;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class PlaylistTrackKey implements Serializable {

    @Column(name = "track_id")
    private Long trackId;

    @Column(name = "playlist_id")
    private Long playlistId;

}
