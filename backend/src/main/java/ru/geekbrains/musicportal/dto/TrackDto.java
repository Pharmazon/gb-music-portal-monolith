package ru.geekbrains.musicportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.band.Band;
import ru.geekbrains.musicportal.entity.group.Category;
import ru.geekbrains.musicportal.entity.track.Track;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TrackDto extends AbstractDto {

    @JsonIgnore
    private Band band;

    @JsonIgnore
    private Collection<Category> categories;

    @JsonIgnore
    private String fileLink;

    private String bandName;
    private Long bandId;
    private Long liked;

    public TrackDto(Track track) {
        super.setId(track.getId());
        super.setName(track.getName());
        super.setDescription(track.getDescription());
        band = track.getBand();
        categories = track.getCategories();
        fileLink = track.getFileLink();
        setCreationDate(track.getCreationDate());
        setLastUpdate(track.getLastUpdate());
        bandName = track.getBand().getName();
        bandId = track.getBand().getId();
    }

    // Конструктор для создания DTO с отображение количества лайоков
    public TrackDto(Track track, Long liked) {
        this(track);
        this.liked = liked;
    }
}
