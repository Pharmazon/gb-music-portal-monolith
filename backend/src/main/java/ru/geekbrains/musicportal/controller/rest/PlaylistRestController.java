package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.marker.PlaylistViews;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.service.playlist.PlaylistService;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/api/miraculous/albums")
public class PlaylistRestController {

    private PlaylistService playlistService;

    @Autowired
    public PlaylistRestController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @JsonView(PlaylistViews.List.class)
    @GetMapping
    public Collection<PlaylistDto> getAll() {
        return playlistService.findAllDto();
    }

    @JsonView(PlaylistViews.Single.class)
    @GetMapping("{id}")
    public PlaylistDto getOneById(@PathVariable("id") Long id) {
        return playlistService.findOneDtoById(id);
    }

}
