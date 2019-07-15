package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.marker.TrackViews;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.service.storage.TrackStorage;
import ru.geekbrains.musicportal.service.track.TrackService;
import ru.geekbrains.musicportal.specification.TrackSpecs;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/tracks")
public class TrackRestController {

    private int INITIAL_PAGE = 50;
    private int PAGE_SIZE = 50;
    private TrackService trackService;
    private TrackStorage storage;

    @Autowired
    public TrackRestController(TrackService trackService, TrackStorage storage) {
        this.trackService = trackService;
        this.storage = storage;
    }

    @JsonView(TrackViews.List.class)
    @GetMapping("/")
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
     * @return - коллекция топ-треков (макс 15)
     */
    @JsonView(TrackViews.All.class)
    @GetMapping("/top/{max}")
    public Collection<TrackDto> getTopByLikes(@PathVariable(name = "max", required = false) Integer max) {
        if (max == null || max > 100 || max < 1) max = 15;
        return trackService.getTopTracks(max);
    }

    @JsonView(TrackViews.List.class)
    @GetMapping
    public String trackPage(Model model,
                            @RequestParam(value = "page") Optional<Integer> page,
                            @RequestParam(value = "bandId", required = false) Long bandId,
                            @RequestParam(value = "albumId", required = false) Long albumId,
                            @RequestParam(value = "artistName", required = false) String artistName,
                            @RequestParam(value = "trackName", required = false) String trackName,
                            @RequestParam(value = "playlistName", required = false) String playlistName,
                            @RequestParam(value = "genreName", required = false) String genreName) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Specification<Track> spec = Specification.where(null);
        if (bandId != null) spec.and(TrackSpecs.bandIdEquals(bandId));
        if (albumId != null) spec.and(TrackSpecs.playlistIdEquals(albumId));
        if (artistName != null) spec.and(TrackSpecs.bandNameContains(artistName));
        if (trackName != null) spec.and(TrackSpecs.trackNameContains(trackName));
        if (playlistName != null) spec.and(TrackSpecs.playlistNameContains(playlistName));
        if (genreName != null) spec.and(TrackSpecs.genreNameContains(genreName));

        Page<Track> tracks = trackService.getTracksWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        model.addAttribute("tracks", tracks.getContent());
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPage", tracks.getTotalPages());
        model.addAttribute("bandId", bandId);
        model.addAttribute("albumId", albumId);
        model.addAttribute("artistName", artistName);
        model.addAttribute("trackName", trackName);
        model.addAttribute("playlistName", playlistName);
        model.addAttribute("genreName", genreName);
        return "Success";
    }

    /**
     * Контроллер возвращает содержимое аудиофайла по его идентификатору.
     * @param id Идентифткатор трека в базе
     * @return Возвращает тело файла. Если трека нет в базе, то выдаёт ответ 404 (HttpStatus.NOT_FOUND), если не удалось
     * вытащить файл, то ответ 500 (HttpStatus.INTERNAL_SERVER_ERROR)
     */
    @GetMapping(value = "/play/{id}", produces = "audio/mpeg")
    public ResponseEntity<InputStreamResource> getPlayFile(@PathVariable("id") Long id) {
        Track track = trackService.findTrackById(id);
        if (track == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        try {
            return storage.getTrackFile(track);
        } catch (FileNotFoundException e) {
            log.error("Трек c id={} не найден", id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
