package ru.geekbrains.musicportal.entity.track;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_track_feature_types")
public class TrackFeatureType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(
            mappedBy = "trackFeatureType",
            fetch = FetchType.LAZY)
    private Collection<TrackFeature> trackFeatures;

}
