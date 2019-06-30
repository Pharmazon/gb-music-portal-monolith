package ru.geekbrains.musicportal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.entity.group.Category;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.entity.user.Band;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TrackDto extends AbstractDto {

    private Band band;

    private Collection<Category> categories;

    private String fileLink;

    public TrackDto(Track track) {
        super.setId(track.getId());
        super.setName(track.getName());
        super.setDescription(track.getDescription());
        band = track.getBand();
        categories = track.getCategories();
        fileLink = track.getFileLink();
    }
}
