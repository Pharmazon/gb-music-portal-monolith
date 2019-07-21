package ru.geekbrains.musicportal.dto.artist;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.artist.Artist;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.marker.ArtistViews;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ArtistDto extends AbstractDto {

    @JsonView(ArtistViews.All.class)
    private Long imageId;

    @JsonView(ArtistViews.All.class)
    private Collection<PlaylistDto> playlists;

    public ArtistDto(Artist artist) {
        if (artist == null) return;

        super.setId(artist.getId());
        super.setName(artist.getName());
        super.setDescription(artist.getDescription());
        super.setCreationDate(artist.getCreationDate());
        super.setLastUpdate(artist.getLastUpdate());
        imageId = artist.getImage() == null ? null : artist.getImage().getId();
        Collection<Playlist> playlistsList = artist.getPlaylists();
        this.playlists = playlistsList.stream()
                .map(PlaylistDto::new)
                .collect(Collectors.toList());
    }
}