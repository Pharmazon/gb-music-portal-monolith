package ru.geekbrains.musicportal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.marker.PlaylistViews;
import ru.geekbrains.musicportal.response.PlaylistResponse;
import ru.geekbrains.musicportal.response.common.ResponseWrapper;
import ru.geekbrains.musicportal.service.playlist.PlaylistService;
import ru.geekbrains.musicportal.specification.PlaylistSpecs;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/playlists")
public class PlaylistRestController {

    private final int INITIAL_PAGE = 50;
    private final int PAGE_SIZE = 50;
    private PlaylistService playlistService;

    @Autowired
    public PlaylistRestController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @JsonView(PlaylistViews.All.class)
    @GetMapping
    public ResponseWrapper getAll(@RequestParam(value = "userId", required = false) Long userId) {
        Collection<PlaylistDto> dtos;
        if (userId != null) {
            dtos = playlistService.getAllByUserId(userId);
        } else {
            dtos = playlistService.findAllDtos();
        }
        return  dtos != null ? ResponseWrapper.ok(dtos, PlaylistResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(PlaylistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(PlaylistViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper getOneById(@PathVariable("id") Long id) {
        PlaylistDto dto = playlistService.findOneDtoById(id);
        return dto != null ? ResponseWrapper.ok(dto, PlaylistResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(PlaylistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(PlaylistViews.All.class)
    @GetMapping("{genreName}")
    public ResponseWrapper getAllByGenreName(@PathVariable("genreName") String genreName) {
        Collection<PlaylistDto> dtos = playlistService.findAllByGenreName(genreName);
        return dtos != null ? ResponseWrapper.ok(dtos, PlaylistResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(PlaylistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(PlaylistViews.All.class)
    @GetMapping("top/{max}")
    public ResponseWrapper getTopByLikes(@PathVariable("max") int max) {
        Collection<PlaylistDto> dto = playlistService.getTop(max);
        return dto != null ? ResponseWrapper.ok(dto, PlaylistResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(PlaylistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(PlaylistViews.All.class)
    @GetMapping("filter")
    public ResponseWrapper playlistPage(@RequestParam(value = "page") Optional<Integer> page,
                                        @RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "userId", required = false) Long userId) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Specification<Playlist> spec = Specification.where(null);
        if (name != null) spec.and(PlaylistSpecs.playlistNameContains(name));
        if (userId != null) spec.and(PlaylistSpecs.userIdEquals(userId));

        Page<Playlist> pages = playlistService.getPlaylistsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        if (pages == null) return ResponseWrapper.notFound(PlaylistResponse.ERROR_NOT_FOUND);

        List<Playlist> entities = pages.getContent();
        List<PlaylistDto> dtos = entities.stream()
                .map(artist -> playlistService.convertToDto(artist))
                .collect(Collectors.toList());
        return ResponseWrapper.ok(dtos, PlaylistResponse.SUCCESS_READ);
    }

    @JsonView(PlaylistViews.All.class)
    @DeleteMapping("{id}")
    public ResponseWrapper delete(@PathVariable("id") Long id) {
        boolean deleted = playlistService.deleteById(id);
        return deleted ? ResponseWrapper.success(PlaylistResponse.SUCCESS_DELETED) :
                ResponseWrapper.notFound(PlaylistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(PlaylistViews.All.class)
    @PutMapping
    public ResponseWrapper update(@Valid PlaylistDto dto) {
        Playlist converted = playlistService.convertToEntity(dto);
        Playlist playlist = playlistService.saveOrUpdate(converted);
        return converted != null && playlist != null ? ResponseWrapper.ok(dto, PlaylistResponse.SUCCESS_UPDATED) :
                ResponseWrapper.notFound(PlaylistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(PlaylistViews.All.class)
    @PostMapping
    public ResponseWrapper save(PlaylistDto dto) {
        Playlist convertedEntity = playlistService.convertToEntity(dto);
        Playlist resultEntity = playlistService.saveOrUpdate(convertedEntity);
        return resultEntity != null ? ResponseWrapper.success(PlaylistResponse.SUCCESS_CREATED) :
                ResponseWrapper.notFound(PlaylistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(PlaylistViews.All.class)
    @PostMapping("{playlistId}/add-track")
    public ResponseWrapper addTrackToPlaylist(@PathVariable("playlistId") Long playlistId,
                                              @RequestParam("trackId") Long trackId) {
        PlaylistDto playlistDto = playlistService.addTrackToPlaylist(playlistId, trackId);
        return playlistDto != null ? ResponseWrapper.success(PlaylistResponse.TRACK_SUCCESS_ADDED) :
                ResponseWrapper.notFound(PlaylistResponse.TRACK_ADD_ERROR);
    }

    @JsonView(PlaylistViews.All.class)
    @DeleteMapping("{playlistId}/delete-track")
    public ResponseWrapper deleteTrackFromPlaylist(@PathVariable("playlistId") Long playlistId,
                                                   @RequestParam("trackId") Long trackId) {
        PlaylistDto playlistDto = playlistService.deleteTrackFromPlaylist(playlistId, trackId);
        return playlistDto != null ? ResponseWrapper.success(PlaylistResponse.TRACK_SUCCESS_DELETED) :
                ResponseWrapper.notFound(PlaylistResponse.TRACK_DELETE_ERROR);
    }
}