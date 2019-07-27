package ru.geekbrains.musicportal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.track.TrackDto;
import ru.geekbrains.musicportal.entity.track.Track;
import ru.geekbrains.musicportal.marker.AlbumViews;
import ru.geekbrains.musicportal.marker.TrackViews;
import ru.geekbrains.musicportal.response.TrackResponse;
import ru.geekbrains.musicportal.response.common.ResponseWrapper;
import ru.geekbrains.musicportal.service.storage.TrackStorage;
import ru.geekbrains.musicportal.service.track.TrackService;
import ru.geekbrains.musicportal.specification.TrackSpecs;

import javax.validation.Valid;
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
    public ResponseWrapper getAll(@RequestParam(value = "artistId", required = false) Long artistId) {
        Collection<TrackDto> dtos;
        if (artistId != null) {
            dtos = trackService.getAllByArtistId(artistId);
        } else {
            dtos = trackService.findAllDtos();
        }
        return dtos != null ? ResponseWrapper.ok(dtos, TrackResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
    }

    @JsonView(TrackViews.All.class)
    @GetMapping("{genreName}")
    public ResponseWrapper getAllByGenreName(@PathVariable("genreName") String genreName) {
        Collection<TrackDto> dtos = trackService.findAllByGenreName(genreName);
        return dtos != null ? ResponseWrapper.ok(dtos, TrackResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
    }

    @JsonView(TrackViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper getOneById(@PathVariable("id") Long id) {
        TrackDto dto = trackService.findOneDtoById(id);
        return dto != null ? ResponseWrapper.ok(dto, TrackResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
    }

    @JsonView(TrackViews.All.class)
    @GetMapping("top/{max}")
    public ResponseWrapper getTopByLikes(@PathVariable("max") int max) {
        Collection<TrackDto> dto = trackService.getTop(max);
        return dto != null ? ResponseWrapper.ok(dto, TrackResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
    }

    @JsonView(TrackViews.All.class)
    @GetMapping("filter")
    public ResponseWrapper trackPage(@RequestParam(value = "page") Optional<Integer> page,
                                     @RequestParam(value = "artist_id", required = false) Long artistId,
                                     @RequestParam(value = "name", required = false) String trackName) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Specification<Track> spec = Specification.where(null);
        if (artistId != null) spec.and(TrackSpecs.artistIdEquals(artistId));
        if (trackName != null) spec.and(TrackSpecs.trackNameContains(trackName));
        Page<Track> pages = trackService.getTracksWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        if (pages == null) return ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);

        List<Track> entities = pages.getContent();
        List<TrackDto> dtos = entities.stream()
                .map(artist -> trackService.convertToDto(artist))
                .collect(Collectors.toList());
        return ResponseWrapper.ok(dtos, TrackResponse.SUCCESS_READ);
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
        if (!optional.isPresent()) return ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
        try {
            return ResponseWrapper.ok(storage.getTrackFile(optional.get()), TrackResponse.SUCCESS_READ);
        } catch (FileNotFoundException e) {
            String message = "Трек c id=" + id + " не найден";
            log.error(message);
            return ResponseWrapper.error(message);
        }
    }

    @JsonView(AlbumViews.All.class)
    @DeleteMapping("{id}")
    public ResponseWrapper delete(@PathVariable("id") Long id) {
        boolean deleted = trackService.deleteById(id);
        return deleted ? ResponseWrapper.success(TrackResponse.SUCCESS_DELETED) :
                ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
    }

    @JsonView(AlbumViews.All.class)
    @PutMapping
    public ResponseWrapper update(@Valid TrackDto dto) {
        Track converted = trackService.convertToEntity(dto);
        Track article = trackService.saveOrUpdate(converted);
        return converted != null && article != null ? ResponseWrapper.ok(dto, TrackResponse.SUCCESS_UPDATED) :
                ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
    }

    @JsonView(AlbumViews.All.class)
    @PostMapping
    public ResponseWrapper save(TrackDto dto) {
        Track convertedEntity = trackService.convertToEntity(dto);
        Track resultEntity = trackService.saveOrUpdate(convertedEntity);
        return resultEntity != null ? ResponseWrapper.success(TrackResponse.SUCCESS_CREATED) :
                ResponseWrapper.notFound(TrackResponse.ERROR_NOT_FOUND);
    }
}
