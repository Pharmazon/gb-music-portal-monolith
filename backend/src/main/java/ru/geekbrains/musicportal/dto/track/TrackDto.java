package ru.geekbrains.musicportal.dto.track;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.dto.group.CategoryDto;
import ru.geekbrains.musicportal.dto.marker.TrackViews;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.group.Category;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.entity.track.TrackFeature;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TrackDto extends AbstractDto {

    @JsonIgnore
    private String bandName;

    private Long bandId;

    private Long liked;

    @JsonIgnore
    private String fileLink;

    @JsonView(TrackViews.Single.class)
    private Collection<TrackFeatureDto> trackFeatures;

    @JsonIgnore
    private Collection<CategoryDto> categories;

    private Collection<PlaylistDto> playlists;

    public TrackDto(Track track, Long liked) {
        this(track);
        this.liked = liked;
    }

    public TrackDto(Track track) {
        super.setId(track.getId());
        super.setName(track.getName());
        super.setDescription(track.getDescription());
        bandId = track.getBand().getId();
        bandName = track.getBand().getName();
        fileLink = track.getFileLink();
        Collection<TrackFeature> trackFeaturesList = track.getTrackFeatures();
        trackFeatures = trackFeaturesList.stream()
                .map(TrackFeatureDto::new)
                .collect(Collectors.toList());
        Collection<Category> categoriesList = track.getCategories();
        categories = categoriesList.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }
}
