package ru.geekbrains.musicportal.dto.playlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.playlist.PlaylistFeature;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlaylistFeatureDto extends AbstractDto {

    private String playlistFeatureType;
    private String value;

    public PlaylistFeatureDto(PlaylistFeature feature){
        super.setId(feature.getId());
        super.setName(feature.getName());
        super.setDescription(feature.getDescription());
        playlistFeatureType = feature.getPlaylistFeatureType().getName();
        value = feature.getValue();
    }
}