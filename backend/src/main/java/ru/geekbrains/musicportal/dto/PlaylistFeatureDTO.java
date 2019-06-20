package ru.geekbrains.musicportal.dto;

import lombok.Data;
import ru.geekbrains.musicportal.entity.database.PlaylistFeature;

@Data
public class PlaylistFeatureDTO {
    private Long id;
    private String playlistFeatureType;
    private String value;

    public void fillByEntity(PlaylistFeature feature){
        id = feature.getId();
        playlistFeatureType = feature.getPlaylistFeatureType().getTitle();
        value = feature.getValue();
    }
}
