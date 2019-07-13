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
import ru.geekbrains.musicportal.util.ConfigUrl;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TrackDto extends AbstractDto {

    @JsonView(TrackViews.All.class)
    private String bandName;

    @JsonView(TrackViews.All.class)
    private Long bandId;

    @JsonView(TrackViews.All.class)
    private Long liked;

    @JsonIgnore
    private String fileLink;

    @JsonView(TrackViews.All.class)
    private String url;

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
        url = ConfigUrl.serverUrl + ConfigUrl.apiPath + ConfigUrl.trackPath + track.getId();
    }
}
