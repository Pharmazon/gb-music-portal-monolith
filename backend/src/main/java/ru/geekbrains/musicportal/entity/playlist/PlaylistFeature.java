package ru.geekbrains.musicportal.entity.playlist;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_playlist_feature")
@EqualsAndHashCode(callSuper = true)
public class PlaylistFeature extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_feature_type_id")
    private PlaylistFeatureType playlistFeatureType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

}
