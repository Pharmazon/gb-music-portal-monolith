package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.album.AlbumDto;
import ru.geekbrains.musicportal.entity.album.Album;
import ru.geekbrains.musicportal.marker.AlbumViews;
import ru.geekbrains.musicportal.response.AlbumResponse;
import ru.geekbrains.musicportal.response.common.ResponseWrapper;
import ru.geekbrains.musicportal.service.album.AlbumService;
import ru.geekbrains.musicportal.service.blog.LikeServiceImpl;
import ru.geekbrains.musicportal.specification.AlbumSpecs;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/albums")
public class AlbumRestController {

    private final int INITIAL_PAGE = 50;
    private final int PAGE_SIZE = 50;
    private AlbumService albumService;
    private LikeServiceImpl likeService;

    @Autowired
    public AlbumRestController(AlbumService albumService, LikeServiceImpl likeService) {
        this.albumService = albumService;
        this.likeService = likeService;
    }

    @JsonView(AlbumViews.All.class)
    @GetMapping
    public ResponseWrapper getAll() {
        Collection<AlbumDto> dtos = albumService.findAllDtos();
        if (dtos != null) {
            return ResponseWrapper.ok(dtos, AlbumResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(AlbumResponse.ERROR_NOT_FOUND);
    }

    @JsonView(AlbumViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper getOneById(@PathVariable("id") Long id) {
        AlbumDto dto = albumService.findOneDtoById(id);
        if (dto != null) {
            return ResponseWrapper.ok(dto, AlbumResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(AlbumResponse.ERROR_NOT_FOUND);
    }

    @JsonView(AlbumViews.All.class)
    @GetMapping("top-15-albums")
    public ResponseWrapper getTopByLikes() {
        Collection<AlbumDto> dto = albumService.getTop(15);
        if (dto != null) {
            return ResponseWrapper.ok(dto, AlbumResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(AlbumResponse.ERROR_NOT_FOUND);
    }

    @JsonView(AlbumViews.All.class)
    @GetMapping("filter")
    public ResponseWrapper playlistPage(@RequestParam(value = "page") Optional<Integer> page,
                                                              @RequestParam(value = "name", required = false) String name) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Specification<Album> spec = Specification.where(null);
        if (name != null) spec.and(AlbumSpecs.playlistNameContains(name));
        Page<Album> pages = albumService.getAlbumsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        if (pages != null) {
            List<Album> entities = pages.getContent();
            List<AlbumDto> dtos = entities.stream()
                    .map(artist -> albumService.convertToDto(artist))
                    .collect(Collectors.toList());
            return ResponseWrapper.ok(dtos, AlbumResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(AlbumResponse.ERROR_NOT_FOUND);
    }
}