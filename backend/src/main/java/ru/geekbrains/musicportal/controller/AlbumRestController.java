package ru.geekbrains.musicportal.controller;

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
import ru.geekbrains.musicportal.service.like.LikeServiceImpl;
import ru.geekbrains.musicportal.specification.AlbumSpecs;

import javax.validation.Valid;
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
    public AlbumRestController(AlbumService albumService,
                               LikeServiceImpl likeService) {
        this.albumService = albumService;
        this.likeService = likeService;
    }

    @JsonView(AlbumViews.All.class)
    @GetMapping
    public ResponseWrapper getAll(@RequestParam(value = "artistId", required = false) Long artistId) {
        Collection<AlbumDto> dtos;
        if (artistId != null) {
            dtos = albumService.getAllByArtistId(artistId);
        } else {
            dtos = albumService.findAllDtos();
        }
        return dtos != null ? ResponseWrapper.ok(dtos, AlbumResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(AlbumResponse.ERROR_NOT_FOUND);
    }

    @JsonView(AlbumViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper getOneById(@PathVariable("id") Long id) {
        AlbumDto dto = albumService.findOneDtoById(id);
        return  dto != null ? ResponseWrapper.ok(dto, AlbumResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(AlbumResponse.ERROR_NOT_FOUND);
    }

    @JsonView(AlbumViews.All.class)
    @GetMapping("{genreName}")
    public ResponseWrapper getAllByGenreName(@PathVariable("genreName") String genreName) {
        Collection<AlbumDto> dtos = albumService.findAllByGenreName(genreName);
        return dtos != null ? ResponseWrapper.ok(dtos, AlbumResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(AlbumResponse.ERROR_NOT_FOUND);
    }

    @JsonView(AlbumViews.All.class)
    @GetMapping("top/{max}")
    public ResponseWrapper getTopByLikes(@PathVariable("max") int max) {
        Collection<AlbumDto> dto = albumService.getTop(max);
        return dto != null ? ResponseWrapper.ok(dto, AlbumResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(AlbumResponse.ERROR_NOT_FOUND);
    }

    @JsonView(AlbumViews.All.class)
    @GetMapping("filter")
    public ResponseWrapper playlistPage(@RequestParam(value = "page") Optional<Integer> page,
                                        @RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "artistId", required = false) Long artistId) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Specification<Album> spec = Specification.where(null);
        if (name != null) spec.and(AlbumSpecs.playlistNameContains(name));
        if (artistId != null) spec.and(AlbumSpecs.artistIdEquals(artistId));
        Page<Album> pages = albumService.getAlbumsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        if (pages == null) return ResponseWrapper.notFound(AlbumResponse.ERROR_NOT_FOUND);

        List<Album> entities = pages.getContent();
        List<AlbumDto> dtos = entities.stream()
                .map(artist -> albumService.convertToDto(artist))
                .collect(Collectors.toList());
        return ResponseWrapper.ok(dtos, AlbumResponse.SUCCESS_READ);
    }

    @JsonView(AlbumViews.All.class)
    @DeleteMapping("{id}")
    public ResponseWrapper delete(@PathVariable("id") Long id) {
        boolean deleted = albumService.deleteById(id);
        return deleted ? ResponseWrapper.success(AlbumResponse.SUCCESS_DELETED) :
                ResponseWrapper.notFound(AlbumResponse.ERROR_NOT_FOUND);
    }

    @JsonView(AlbumViews.All.class)
    @PutMapping
    public ResponseWrapper update(@Valid AlbumDto dto) {
        Album converted = albumService.convertToEntity(dto);
        Album article = albumService.saveOrUpdate(converted);
        return converted != null && article != null ? ResponseWrapper.ok(dto, AlbumResponse.SUCCESS_UPDATED) :
                ResponseWrapper.notFound(AlbumResponse.ERROR_NOT_FOUND);
    }

    @JsonView(AlbumViews.All.class)
    @PostMapping
    public ResponseWrapper save(AlbumDto dto) {
        Album convertedEntity = albumService.convertToEntity(dto);
        Album resultEntity = albumService.saveOrUpdate(convertedEntity);
        return resultEntity != null ? ResponseWrapper.success(AlbumResponse.SUCCESS_CREATED) :
                ResponseWrapper.notFound(AlbumResponse.ERROR_NOT_FOUND);
    }
}