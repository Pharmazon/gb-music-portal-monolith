package ru.geekbrains.musicportal.dto.album;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.album.Album;
import ru.geekbrains.musicportal.entity.album.AlbumFeature;
import ru.geekbrains.musicportal.entity.track.AlbumTrack;
import ru.geekbrains.musicportal.marker.AlbumViews;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AlbumDto extends AbstractDto {

    @JsonView(AlbumViews.All.class)
    private Collection<TrackDto> tracks;

    @JsonView(AlbumViews.All.class)
    private Long imageId;

    @JsonView(AlbumViews.All.class)
    private Collection<AlbumFeatureDto> albumFeatureDtos;

    public AlbumDto(Album album) {
        if (album == null) return;

        super.setId(album.getId());
        super.setName(album.getName());
        super.setDescription(album.getDescription());
        super.setCreationDate(album.getCreationDate());
        super.setLastUpdate(album.getLastUpdate());
        Collection<AlbumTrack> albumTrackList = album.getAlbumTracks();
        tracks = albumTrackList.stream()
                .map(albumTrack -> new TrackDto(albumTrack.getTrack()))
                .collect(Collectors.toList());
        imageId = album.getImage() != null ? album.getImage().getId() : null;
        Collection<AlbumFeature> albumFeatures = album.getAlbumFeatures();
        albumFeatureDtos = albumFeatures.stream()
                .map(AlbumFeatureDto::new)
                .collect(Collectors.toList());
    }

}
