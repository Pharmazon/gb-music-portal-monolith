package ru.geekbrains.musicportal.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PLAYLIST_FEATURE")
public class PlaylistFeature extends AbstractEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PLAYLIST_ID", referencedColumnName = "ID")
    private Playlist playlist;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PLAYLIST_FEATURE_TYPE_ID", referencedColumnName = "ID")
    private PlaylistFeatureType playlistFeatureType;
}
