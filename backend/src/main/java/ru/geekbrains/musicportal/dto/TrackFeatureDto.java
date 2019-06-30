package ru.geekbrains.musicportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.track.TrackFeature;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TrackFeatureDto extends AbstractDto {

    private String musicFeatureType;

    private String value;

    public void MusicFeatureDto(TrackFeature feature){
        super.setId(feature.getId());
        super.setName(feature.getName());
        super.setDescription(feature.getDescription());
        musicFeatureType = feature.getTrackFeatureType().getDescription();
        value = feature.getName();
    }
}
