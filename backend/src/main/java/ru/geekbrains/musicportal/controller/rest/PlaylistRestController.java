package ru.geekbrains.musicportal.controller.rest;

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
import ru.geekbrains.musicportal.service.blog.LikeServiceImpl;
import ru.geekbrains.musicportal.service.playlist.PlaylistService;
import ru.geekbrains.musicportal.specification.PlaylistSpecs;

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
    private LikeServiceImpl likeService;

    @Autowired
    public PlaylistRestController(PlaylistService playlistService, LikeServiceImpl likeService) {
        this.playlistService = playlistService;
        this.likeService = likeService;
    }

    @JsonView(PlaylistViews.All.class)
    @GetMapping
    public ResponseWrapper getAll() {
        Collection<PlaylistDto> dtos = playlistService.findAllDtos();
        if (dtos != null) {
            return ResponseWrapper.ok(dtos, PlaylistResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(PlaylistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(PlaylistViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper getOneById(@PathVariable("id") Long id) {
        PlaylistDto dto = playlistService.findOneDtoById(id);
        if (dto != null) {
            return ResponseWrapper.ok(dto, PlaylistResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(PlaylistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(PlaylistViews.All.class)
    @GetMapping("top-15-playlists")
    public ResponseWrapper getTopByLikes() {
        Collection<PlaylistDto> dto = playlistService.getTop(15);
        if (dto != null) {
            return ResponseWrapper.ok(dto, PlaylistResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(PlaylistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(PlaylistViews.All.class)
    @GetMapping("filter")
    public ResponseWrapper playlistPage(@RequestParam(value = "page") Optional<Integer> page,
                                        @RequestParam(value = "name", required = false) String name) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Specification<Playlist> spec = Specification.where(null);
        if (name != null) spec.and(PlaylistSpecs.playlistNameContains(name));
        Page<Playlist> pages = playlistService.getPlaylistsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        if (pages != null) {
            List<Playlist> entities = pages.getContent();
            List<PlaylistDto> dtos = entities.stream()
                    .map(artist -> playlistService.convertToDto(artist))
                    .collect(Collectors.toList());
            return ResponseWrapper.ok(dtos, PlaylistResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(PlaylistResponse.ERROR_NOT_FOUND);
    }
}