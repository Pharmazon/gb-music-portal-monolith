package ru.geekbrains.musicportal.dto;

import lombok.Data;
import ru.geekbrains.musicportal.entity.database.MusicFeature;

@Data
public class MusicFeatureDTO {
    private Long id;
    private String musicFeatureType;
    private String value;

    public void fillByEntity(MusicFeature feature){
        id = feature.getId();
        musicFeatureType = feature.getMusicFeatureType().getTitle();
        value = feature.getValue();
    }
}
