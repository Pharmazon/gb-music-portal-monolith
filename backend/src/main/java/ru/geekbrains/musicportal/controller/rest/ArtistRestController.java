package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.artist.ArtistDto;
import ru.geekbrains.musicportal.entity.artist.Artist;
import ru.geekbrains.musicportal.marker.ArtistViews;
import ru.geekbrains.musicportal.response.ArticleResponse;
import ru.geekbrains.musicportal.response.ArtistResponse;
import ru.geekbrains.musicportal.response.common.ResponseWrapper;
import ru.geekbrains.musicportal.service.artist.ArtistService;
import ru.geekbrains.musicportal.specification.ArtistSpecs;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/artists")
public class ArtistRestController {

    private final int INITIAL_PAGE = 50;
    private final int PAGE_SIZE = 50;

    private ArtistService artistService;

    @Autowired
    public ArtistRestController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @JsonView(ArtistViews.All.class)
    @GetMapping
    public ResponseWrapper<Collection<ArtistDto>> getAll() {
        Collection<ArtistDto> dtos = artistService.findAllDtos();
        if (dtos != null) {
            return ResponseWrapper.ok(dtos, ArtistResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(ArtistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArtistViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper<ArtistDto> getOne(@PathVariable("id") Long id) {
        ArtistDto dto = artistService.findOneDtoById(id);
        if (dto != null) {
            return ResponseWrapper.ok(dto, ArtistResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(ArtistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArtistViews.All.class)
    @PutMapping
    public ResponseWrapper<ArtistDto> update(@Valid ArtistDto dto) {
        Artist converted = artistService.convertToEntity(dto);
        Artist artist = artistService.saveOrUpdate(converted);
        if (converted != null && artist != null) {
            return ResponseWrapper.ok(dto, ArticleResponse.SUCCESS_UPDATED);
        }
        return ResponseWrapper.notFound(ArticleResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArtistViews.All.class)
    @DeleteMapping("{id}")
    public ResponseWrapper delete(@PathVariable("id") Long id) {
        boolean deleted = artistService.deleteById(id);
        if (deleted) {
            return ResponseWrapper.success(ArticleResponse.SUCCESS_DELETED);
        }
        return ResponseWrapper.notFound(ArticleResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArtistViews.All.class)
    @GetMapping("music")
    public ResponseWrapper<Collection<ArtistDto>> artistPage(@RequestParam(value = "page") Optional<Integer> page,
                                                             @RequestParam(value = "artistName", required = false) String artistName) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Specification<Artist> spec = Specification.where(null);
        if (artistName != null) spec.and(ArtistSpecs.artistNameContains(artistName));
        Page<Artist> pages = artistService.getArtistsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        if (pages != null) {
            List<Artist> artists = pages.getContent();
            List<ArtistDto> dtos = artists.stream()
                    .map(artist -> artistService.convertToDto(artist))
                    .collect(Collectors.toList());
            return ResponseWrapper.ok(dtos, ArtistResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(ArticleResponse.ERROR_NOT_FOUND);
    }

}

