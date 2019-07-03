package ru.geekbrains.musicportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.TrackDto;
import ru.geekbrains.musicportal.service.track.TrackService;

import java.util.List;

@RestController
public class TrackController {
    private final TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    /**
     * Контроллер выдаёт топ треков (по колличеству лайков)
     * @return
     */
    @RequestMapping(value = "/api/miraculous/top/{max}", method = RequestMethod.GET)
    public List<TrackDto> getTopByLikes(@PathVariable(name = "max", required = false) Integer max){
        if (max == null || max > 100 || max < 1) max = 15;
        return trackService.getTopTracks(max);
    }
}
