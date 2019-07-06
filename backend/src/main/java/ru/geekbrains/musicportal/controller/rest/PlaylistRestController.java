package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.marker.PlaylistViews;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.service.playlist.PlaylistService;

import java.util.Optional;

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
    public java.util.List getAll() {
        return (java.util.List) playlistService.findAll();
    }

    @JsonView(PlaylistViews.Single.class)
    @GetMapping("{id}")
    public PlaylistDto getOneById(@PathVariable("id") Long id) {
        Optional<Playlist> optional = playlistService.findById(id);
        return optional.map(PlaylistDto::new).orElse(null);
    }

}
