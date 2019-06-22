package ru.geekbrains.musicportal.entity.playlist;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_playlist_feature_type")
@EqualsAndHashCode(callSuper = true)
public class PlaylistFeatureType extends AbstractEntity {

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "playlistFeatureType")
    private Collection<PlaylistFeature> playlistFeatures;
}
