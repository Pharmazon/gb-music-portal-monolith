package ru.geekbrains.musicportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.playlist.Playlist;

import java.util.Collection;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlaylistDto extends AbstractDto {

    private UserDto owner;

    private Collection<TrackDto> tracks;

    private Map<String, Integer> categories;

    private Map<String, String> features;

    private int currentTrack = 0;

    public void PlaylistDto(Playlist playlist){
        super.setId(playlist.getId());
        super.setName(playlist.getName());
        super.setDescription(playlist.getDescription());
        owner = new UserDto(playlist.getUser());
    }

}
