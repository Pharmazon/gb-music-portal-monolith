package ru.geekbrains.musicportal.dto.track;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.config.UrlConfig;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.marker.TrackViews;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TrackDto extends AbstractDto {

    @JsonView(TrackViews.All.class)
    private String artistName;

    @JsonView(TrackViews.All.class)
    private Long artistId;

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
        artistId = track.getArtist().getId();
        artistName = track.getArtist().getName();
        fileLink = track.getFileLink();
        url = UrlConfig.serverUrl + UrlConfig.apiPath + UrlConfig.trackPath + track.getId();
    }
}
