package ru.geekbrains.musicportal.entity.track;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_track_features")
@EqualsAndHashCode(callSuper = true)
public class TrackFeature extends AbstractEntity {

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    private Track track;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_feature_type_id")
    private TrackFeatureType trackFeatureType;

}
