package ru.geekbrains.musicportal.entity.track;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_track_feature_types")
@EqualsAndHashCode(callSuper = true)
public class TrackFeatureType extends AbstractEntity {

    @OneToMany(
            mappedBy = "trackFeatureType",
            fetch = FetchType.LAZY)
    private Collection<TrackFeature> trackFeatures;

}
