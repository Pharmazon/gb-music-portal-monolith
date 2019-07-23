package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.marker.PlaylistViews;
import ru.geekbrains.musicportal.marker.TrackViews;
import ru.geekbrains.musicportal.response.TrackResponse;
import ru.geekbrains.musicportal.response.common.ResponseWrapper;
import ru.geekbrains.musicportal.service.storage.TrackStorage;
import ru.geekbrains.musicportal.service.track.TrackService;
import ru.geekbrains.musicportal.specification.TrackSpecs;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/tracks")
public class TrackRestController {

    private final int INITIAL_PAGE = 50;
    private final int PAGE_SIZE = 50;
    private TrackService trackService;
    private TrackStorage storage;

    @Autowired
    public TrackRestController(TrackService trackService,
                               TrackStorage storage) {
        this.trackService = trackService;
        this.storage = storage;
    }

    @JsonView(TrackViews.All.class)
    @GetMapping
    public ResponseWrapper getAll() {
        Collection<TrackDto> dtos = trackService.findAllDtos();
        if (dtos != null) {
            return ResponseWrapper.ok(dtos, TrackResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
    }

    @JsonView(TrackViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper getOneById(@PathVariable("id") Long id) {
        TrackDto dto = trackService.findOneDtoById(id);
        if (dto != null) {
            return ResponseWrapper.ok(dto, TrackResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
    }

    @JsonView(PlaylistViews.All.class)
    @GetMapping("top-15-tracks")
    public ResponseWrapper getTopByLikes() {
        Collection<TrackDto> dto = trackService.getTop(15);
        if (dto != null) {
            return ResponseWrapper.ok(dto, TrackResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
    }

    @JsonView(TrackViews.All.class)
    @GetMapping("filter")
    public ResponseWrapper trackPage(@RequestParam(value = "page") Optional<Integer> page,
                                     @RequestParam(value = "artistId", required = false) Long artistId,
                                     @RequestParam(value = "playlistId", required = false) Long playlistId,
                                     @RequestParam(value = "trackName", required = false) String trackName) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Specification<Track> spec = Specification.where(null);
        if (artistId != null) spec.and(TrackSpecs.artistIdEquals(artistId));
        if (playlistId != null) spec.and(TrackSpecs.playlistIdEquals(playlistId));
        if (trackName != null) spec.and(TrackSpecs.trackNameContains(trackName));
        Page<Track> pages = trackService.getTracksWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        if (pages != null) {
            List<Track> entities = pages.getContent();
            List<TrackDto> dtos = entities.stream()
                    .map(artist -> trackService.convertToDto(artist))
                    .collect(Collectors.toList());
            return ResponseWrapper.ok(dtos, TrackResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
    }

    /**
     * Контроллер возвращает содержимое аудиофайла по его идентификатору.
     * @param id Идентифткатор трека в базе
     * @return Возвращает тело файла. Если трека нет в базе, то выдаёт ответ 404 (HttpStatus.NOT_FOUND), если не удалось
     * вытащить файл, то ответ 500 (HttpStatus.INTERNAL_SERVER_ERROR)
     */
    @GetMapping(value = "play/{id}", produces = "audio/mpeg")
    public ResponseWrapper getPlayFile(@PathVariable("id") Long id) {
        Optional<Track> optional = trackService.findOneEntityById(id);
        if (!optional.isPresent()) {
            return ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
        }
        Track track = optional.get();
        try {
            return ResponseWrapper.ok(storage.getTrackFile(track), TrackResponse.SUCCESS_READ);
        } catch (FileNotFoundException e) {
            String message = "Трек c id=" + id + " не найден";
            log.error(message);
            return ResponseWrapper.error(message);
        }
    }

}
