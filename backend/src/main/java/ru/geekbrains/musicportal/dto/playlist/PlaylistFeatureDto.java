package ru.geekbrains.musicportal.dto.playlist;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.playlist.PlaylistFeature;
import ru.geekbrains.musicportal.marker.PlaylistViews;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlaylistFeatureDto extends AbstractDto {

    @JsonView(PlaylistViews.All.class)
    private String playlistFeatureType;

    @JsonView(PlaylistViews.All.class)
    private String value;

    public PlaylistFeatureDto(PlaylistFeature feature){
        super.setId(feature.getId());
        super.setName(feature.getName());
        super.setDescription(feature.getDescription());
        super.setCreationDate(feature.getCreationDate());
        super.setLastUpdate(feature.getLastUpdate());
        playlistFeatureType = feature.getPlaylistFeatureType().getName();
        value = feature.getValue();
    }
}
