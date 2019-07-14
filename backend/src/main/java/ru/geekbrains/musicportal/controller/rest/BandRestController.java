package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.band.BandDto;
import ru.geekbrains.musicportal.dto.marker.BandViews;
import ru.geekbrains.musicportal.service.band.BandService;
import ru.geekbrains.musicportal.service.playlist.PlaylistService;
import ru.geekbrains.musicportal.service.track.TrackService;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/miraculous/api/artists")
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
        return bandService.findAllDto();
    }

    @JsonView(BandViews.All.class)
    @GetMapping("{artistId}")
    public BandDto getOneArtistById(@PathVariable("artistId") Long artistId) {
        return bandService.findOneDtoById(artistId);
    }

}

