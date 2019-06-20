package ru.geekbrains.musicportal.entity.database;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "playlist_feature")
@Data
public class PlaylistFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "feature_id")
    private PlaylistFeatureType playlistFeatureType;

    @OneToMany
    @NotNull
    @JoinColumn(name = "track_id")
    private Track track;

    @Column(name = "value")
    private String value;
}
