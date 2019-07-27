package ru.geekbrains.musicportal.dto.playlist;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.entity.playlist.PlaylistFeature;
import ru.geekbrains.musicportal.entity.track.PlaylistTrack;
import ru.geekbrains.musicportal.marker.PlaylistViews;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlaylistDto extends AbstractDto {

    @JsonView(PlaylistViews.All.class)
    private Collection<TrackDto> tracks;

    @JsonView(PlaylistViews.All.class)
    private Long imageId;

    @JsonView(PlaylistViews.All.class)
    private Collection<PlaylistFeatureDto> playlistFeatureDtos;

    @JsonView(PlaylistViews.All.class)
    private Long userId;

    @JsonView(PlaylistViews.All.class)
    private Boolean isDeleted;

    public PlaylistDto(Playlist playlist) {
        if (playlist == null) return;

        super.setId(playlist.getId());
        super.setName(playlist.getName());
        super.setDescription(playlist.getDescription());
        super.setCreationDate(playlist.getCreationDate());
        super.setLastUpdate(playlist.getLastUpdate());
        Collection<PlaylistTrack> playlistTracksList = playlist.getPlaylistTracks();
        tracks = playlistTracksList.stream()
                .map(playTrack -> new TrackDto(playTrack.getTrack()))
                .collect(Collectors.toList());
        imageId = playlist.getImage() != null ? playlist.getImage().getId() : null;
        userId = playlist.getUser() != null ? playlist.getUser().getId() : null;
        Collection<PlaylistFeature> playlistFeatures = playlist.getPlaylistFeatures();
        playlistFeatureDtos = playlistFeatures.stream()
                .map(PlaylistFeatureDto::new)
                .collect(Collectors.toList());
        isDeleted = playlist.getIsDeleted();
    }

}
