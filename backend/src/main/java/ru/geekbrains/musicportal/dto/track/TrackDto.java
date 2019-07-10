package ru.geekbrains.musicportal.dto.track;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.dto.marker.TrackViews;
import ru.geekbrains.musicportal.entity.track.Track;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TrackDto extends AbstractDto {

    // Временная переменная, пока не будет понятно как url прокинуть в Dto
    private String serverUrl = "http://localhost:8080/api/miraculous/tracks/play/";

    @JsonView(TrackViews.List.class)
    private String bandName;

    @JsonView(TrackViews.List.class)
    private Long bandId;

    @JsonView(TrackViews.List.class)
    private Long liked;

    @JsonView(TrackViews.List.class)
    private String url;

    @JsonIgnore
    private String fileLink;

    public TrackDto(Track track, Long liked) {
        this(track);
        this.liked = liked;
    }

    public TrackDto(Track track) {
        if (track == null) return;

        super.setId(track.getId());
        super.setName(track.getName());
        super.setDescription(track.getDescription());
        bandId = track.getBand().getId();
        bandName = track.getBand().getName();
        fileLink = track.getFileLink();
        url = serverUrl + track.getId();
    }
}
