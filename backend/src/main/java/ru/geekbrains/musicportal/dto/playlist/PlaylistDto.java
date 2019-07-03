package ru.geekbrains.musicportal.dto.playlist;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.dto.image.ImageDto;
import ru.geekbrains.musicportal.dto.marker.PlaylistViews;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.dto.user.UserDto;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.entity.playlist.PlaylistFeature;
import ru.geekbrains.musicportal.entity.track.PlaylistTrack;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlaylistDto extends AbstractDto {

    private UserDto user;

    @JsonView(PlaylistViews.Single.class)
    private Collection<TrackDto> playlistTracks;

    private Collection<PlaylistFeatureDto> playlistFeatures;

    private Map<String, String> features;

    private ImageDto image;

    private int currentTrack = 0;

    public PlaylistDto(Playlist playlist){
        super.setId(playlist.getId());
        super.setName(playlist.getName());
        super.setDescription(playlist.getDescription());
        Collection<PlaylistTrack> playlistTracksList = playlist.getPlaylistTracks();
        playlistTracks = playlistTracksList.stream()
                .map(playTrack -> new TrackDto(playTrack.getTrack()))
                .collect(Collectors.toList());
        Collection<PlaylistFeature> playlistFeaturesList = playlist.getPlaylistFeatures();
        playlistFeatures = playlistFeaturesList.stream()
                .map(PlaylistFeatureDto::new)
                .collect(Collectors.toList());
        user = new UserDto(playlist.getUser());
        image = new ImageDto(playlist.getImage());
    }

}
