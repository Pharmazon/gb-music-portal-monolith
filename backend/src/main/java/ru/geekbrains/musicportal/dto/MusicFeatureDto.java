package ru.geekbrains.musicportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.music.MusicFeature;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MusicFeatureDto extends AbstractDto {

    private String musicFeatureType;

    private String value;

    public void MusicFeatureDto(MusicFeature feature){
        super.setId(feature.getId());
        super.setName(feature.getName());
        super.setDescription(feature.getDescription());
        musicFeatureType = feature.getMusicFeatureType().getDescription();
        value = feature.getName();
    }
}
