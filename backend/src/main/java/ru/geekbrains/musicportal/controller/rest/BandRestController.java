package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.band.BandDto;
import ru.geekbrains.musicportal.dto.marker.BandViews;
import ru.geekbrains.musicportal.dto.marker.PlaylistViews;
import ru.geekbrains.musicportal.dto.marker.TrackViews;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.band.Band;
import ru.geekbrains.musicportal.service.playlist.PlaylistService;
import ru.geekbrains.musicportal.service.track.TrackService;
import ru.geekbrains.musicportal.service.user.BandService;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/miraculous/artists")
public class BandRestController {

    private BandService bandService;
    private PlaylistService playlistService;
    private TrackService trackService;

    @Autowired
    public BandRestController(BandService bandService,
                              PlaylistService playlistService,
                              TrackService trackService) {
        this.bandService = bandService;
        this.playlistService = playlistService;
        this.trackService = trackService;
    }

    @JsonView(BandViews.List.class)
    @GetMapping
    public Collection<BandDto> getAllArtists() {
        return bandService.findAll();
    }

    @JsonView(PlaylistViews.List.class)
    @GetMapping("{artistId}")
    public BandDto getOneArtistById(@PathVariable("artistId") Long artistId) {
        Optional<Band> optional = bandService.findById(artistId);
        return optional.map(BandDto::new).orElse(null);
    }

    @JsonView(PlaylistViews.Single.class)
    @GetMapping("{artistId}/albums/{albumId}")
    public PlaylistDto getOneByAlbumIdAndBandId(@PathVariable("artistId") Long artistId,
                                                @PathVariable("albumId") Long albumId) {
        return playlistService.findOneByIdAndBandId(albumId, artistId);
    }

    @JsonView(TrackViews.Single.class)
    @GetMapping("{artistId}/albums/{albumId}/tracks/{trackId}")
    public TrackDto getOneTrackByIdAndAlbumIdAndArtistId(@PathVariable("artistId") Long artistId,
                                                         @PathVariable("albumId") Long albumId,
                                                         @PathVariable("trackId") Long trackId) {

        return null;
    }
}

