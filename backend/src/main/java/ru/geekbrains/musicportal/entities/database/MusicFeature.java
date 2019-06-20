package ru.geekbrains.musicportal.entities.database;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "music_feature")
@Data
public class MusicFeature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "feature_id")
    private MusicFeatureType musicFeatureType;

    @OneToMany
    @NotNull
    @JoinColumn(name = "track_id")
    private Track track;

    @Column(name = "value")
    private String value;
}
