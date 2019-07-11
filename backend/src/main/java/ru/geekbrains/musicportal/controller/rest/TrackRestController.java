package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.marker.TrackViews;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.service.track.TrackService;

import java.util.Collection;

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
        return trackService.findAllDto();
    }

    @JsonView(TrackViews.Single.class)
    @GetMapping("{id}")
    public TrackDto getOneById(@PathVariable("id") Long id) {
        return trackService.findOneDtoById(id);
    }

    /**
     * Контроллер выдаёт топ треков (по колличеству лайков)
     * @return
     */
    @JsonView(TrackViews.All.class)
    @GetMapping("/top/{max}")
    public Collection<TrackDto> getTopByLikes(@PathVariable(name = "max", required = false) Integer max) {
        if (max == null || max > 100 || max < 1) max = 15;
        return trackService.getTopTracks(max);

    }
}
