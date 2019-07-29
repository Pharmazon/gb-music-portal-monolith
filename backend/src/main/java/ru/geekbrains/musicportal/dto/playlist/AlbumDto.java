package ru.geekbrains.musicportal.dto.playlist;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.geekbrains.musicportal.dto.artist.ArtistDto;
import ru.geekbrains.musicportal.dto.comment.CommentDto;
import ru.geekbrains.musicportal.dto.common.AbstractDto;
import ru.geekbrains.musicportal.dto.image.ImageDto;
import ru.geekbrains.musicportal.dto.like.LikeDto;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.album.Album;
import ru.geekbrains.musicportal.entity.track.AlbumTrack;
import ru.geekbrains.musicportal.marker.PlaylistViews;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AlbumDto extends AbstractDto {
    @JsonView(PlaylistViews.All.class)
    private ArtistDto artist;

    @JsonView(PlaylistViews.All.class)
    private Collection<TrackDto> tracks;

    @JsonView(PlaylistViews.All.class)
    private ImageDto coverPicture;

    @JsonView(PlaylistViews.All.class)
    private Double trackDuration;

    @JsonView(PlaylistViews.All.class)
    private Double trackSize;

    @JsonView(PlaylistViews.All.class)
    private List<LikeDto> likes;

    @JsonView(PlaylistViews.All.class)
    private Boolean isFeatured;

    @JsonView(PlaylistViews.All.class)
    private LocalDateTime releaseDate;

    @JsonView(PlaylistViews.All.class)
    private Boolean isInPayedAccess;

    @JsonView(PlaylistViews.All.class)
    private Double price;

    @JsonView(PlaylistViews.All.class)
    private List<CommentDto> comments;

    public AlbumDto(Album album) {
        super.setId(album.getId());
        super.setName(album.getName());
        releaseDate = album.getCreationDate();
        artist = new ArtistDto(album.getArtist());

        for (AlbumTrack albTrack: album.getAlbumTracks()) {
            tracks.add(new TrackDto(albTrack.getTrack()));
        }

        coverPicture = new ImageDto(album.getImage());
        trackDuration = album.getTotalDuration();
        trackSize = album.getTotalSize();
    }
}
