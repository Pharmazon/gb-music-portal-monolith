package ru.geekbrains.musicportal.dto.band;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.band.Band;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.marker.BandViews;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BandDto extends AbstractDto {

    @JsonView(BandViews.All.class)
    private Long imageId;

    @JsonView(BandViews.Single.class)
    private Collection<PlaylistDto> playlists;

    public BandDto(Band band) {
        if (band == null) return;

        super.setId(band.getId());
        super.setName(band.getName());
        super.setDescription(band.getDescription());
        imageId = band.getImage() == null ? null : band.getImage().getId();
        Collection<Playlist> playlistsList = band.getPlaylists();
        this.playlists = playlistsList.stream()
                .map(PlaylistDto::new)
                .collect(Collectors.toList());
    }
}