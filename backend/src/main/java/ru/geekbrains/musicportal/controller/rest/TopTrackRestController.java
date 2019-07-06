package ru.geekbrains.musicportal.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.service.track.TrackService;

import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/api/miraculous/top")
public class TopTrackRestController {

    private TrackService trackService;

    @Autowired
    public TopTrackRestController(TrackService trackService) {
        this.trackService = trackService;
    }

    /**
     * Контроллер выдаёт топ треков (по колличеству лайков)
     * @return - коллекцию trackDto
     */
    @GetMapping("/{max}")
    public Collection<TrackDto> getTopByLikes(@PathVariable(name = "max", required = false) Integer max) {
        if (max == null || max > 100 || max < 1) max = 15;
        return trackService.getTopTracks(max);
    }
}
