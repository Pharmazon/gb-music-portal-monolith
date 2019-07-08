package ru.geekbrains.musicportal.entity.playlist;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_playlist_feature_types")
public class PlaylistFeatureType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "playlistFeatureType")
    private Collection<PlaylistFeature> playlistFeatures;
}
