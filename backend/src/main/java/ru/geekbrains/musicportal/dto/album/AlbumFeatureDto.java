package ru.geekbrains.musicportal.dto.album;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.album.AlbumFeature;
import ru.geekbrains.musicportal.marker.AlbumViews;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AlbumFeatureDto extends AbstractDto {

    @JsonView(AlbumViews.All.class)
    private String albumFeatureType;

    @JsonView(AlbumViews.All.class)
    private String value;

    public AlbumFeatureDto(AlbumFeature feature){
        super.setId(feature.getId());
        super.setName(feature.getName());
        super.setDescription(feature.getDescription());
        super.setCreationDate(feature.getCreationDate());
        super.setLastUpdate(feature.getLastUpdate());
        albumFeatureType = feature.getAlbumFeatureType().getName();
        value = feature.getValue();
    }
}
