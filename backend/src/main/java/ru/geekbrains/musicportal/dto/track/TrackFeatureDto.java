package ru.geekbrains.musicportal.dto.track;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.track.TrackFeature;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TrackFeatureDto extends AbstractDto {

    private String trackFeatureType;
    private String value;

    public TrackFeatureDto(TrackFeature feature){
        super.setId(feature.getId());
        super.setName(feature.getName());
        super.setDescription(feature.getDescription());
        trackFeatureType = feature.getTrackFeatureType().getDescription();
        value = feature.getName();
    }
}
