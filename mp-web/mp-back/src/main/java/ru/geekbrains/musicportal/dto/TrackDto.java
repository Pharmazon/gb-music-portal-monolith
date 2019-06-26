package ru.geekbrains.musicportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.group.Category;
import ru.geekbrains.musicportal.entity.track.MusicGroup;
import ru.geekbrains.musicportal.entity.track.Track;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TrackDto extends AbstractDto {

    private MusicGroup musicGroup;

    private Collection<Category> categories;

    private String fileLink;

    public TrackDto(Track track) {
        super.setId(track.getId());
        super.setName(track.getName());
        super.setDescription(track.getDescription());
        musicGroup = track.getMusicGroup();
        categories = track.getCategories();
        fileLink = track.getFileLink();
    }
}
