package ru.geekbrains.musicportal.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TRACK_IN_PLAYLIST")
public class TrackInPlaylist extends AbstractEntity{
    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PLAYLIST_ID", referencedColumnName = "ID")
    private Playlist playlist;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TRACK_ID", referencedColumnName = "ID")
    private Track track;

}
