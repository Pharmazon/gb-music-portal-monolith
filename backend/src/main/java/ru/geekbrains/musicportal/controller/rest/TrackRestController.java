package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.marker.TrackViews;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.service.track.TrackService;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/miraculous/tracks")
public class TrackRestController {

    private TrackService trackService;

    @Autowired
    public TrackRestController(TrackService trackService) {
        this.trackService = trackService;
    }

    @JsonView(TrackViews.List.class)
    @GetMapping
    public Collection<TrackDto> getAll() {
        return trackService.findAll();
    }

    @JsonView(TrackViews.Single.class)
    @GetMapping("{id}")
    public TrackDto getOneById(@PathVariable("id") Long id) {
        Optional<Track> optional = trackService.findById(id);
        return optional.map(TrackDto::new).orElse(null);
    }
}
