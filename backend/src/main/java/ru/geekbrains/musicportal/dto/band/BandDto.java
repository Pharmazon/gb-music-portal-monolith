package ru.geekbrains.musicportal.dto.band;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.dto.image.ImageDto;
import ru.geekbrains.musicportal.dto.marker.PlaylistViews;
import ru.geekbrains.musicportal.dto.marker.TrackViews;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.band.Band;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.entity.track.Track;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BandDto extends AbstractDto {

    @JsonView(TrackViews.List.class)
    private Collection<TrackDto> tracks;

    private ImageDto image;

    @JsonView(PlaylistViews.List.class)
    private Collection<PlaylistDto> playlists;

    public BandDto(Band band) {
        super.setId(band.getId());
        super.setName(band.getName());
        super.setDescription(band.getDescription());
        Collection<Track> tracksList = band.getTracks();
        tracks = tracksList.stream()
                .map(TrackDto::new)
                .collect(Collectors.toList());
        Collection<Playlist> playlistsList = band.getPlaylists();
        this.playlists = playlistsList.stream()
                .map(PlaylistDto::new)
                .collect(Collectors.toList());
    }
}