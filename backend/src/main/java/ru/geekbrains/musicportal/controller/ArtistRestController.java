package ru.geekbrains.musicportal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.artist.ArtistDto;
import ru.geekbrains.musicportal.entity.artist.Artist;
import ru.geekbrains.musicportal.marker.ArtistViews;
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
    public ResponseWrapper getAll() {
        Collection<ArtistDto> dtos = artistService.findAllDtos();
        if (dtos != null) return ResponseWrapper.ok(dtos, ArtistResponse.SUCCESS_READ);
        return ResponseWrapper.notFound(ArtistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArtistViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper getOne(@PathVariable("id") Long id) {
        ArtistDto dto = artistService.findOneDtoById(id);
        return dto != null ? ResponseWrapper.ok(dto, ArtistResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(ArtistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArtistViews.All.class)
    @GetMapping("{genreName}")
    public ResponseWrapper getAllByGenreName(@PathVariable("genreName") String genreName) {
        Collection<ArtistDto> dtos = artistService.findAllByGenreName(genreName);
        return dtos != null ? ResponseWrapper.ok(dtos, ArtistResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(ArtistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArtistViews.All.class)
    @PutMapping
    public ResponseWrapper update(@Valid ArtistDto dto) {
        Artist converted = artistService.convertToEntity(dto);
        Artist artist = artistService.saveOrUpdate(converted);
        if (converted != null && artist != null) {
            return ResponseWrapper.ok(dto, ArtistResponse.SUCCESS_UPDATED);
        }
        return ResponseWrapper.notFound(ArtistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArtistViews.All.class)
    @GetMapping("top/{max}")
    public ResponseWrapper getTopByLikes(@PathVariable("max") int max) {
        Collection<ArtistDto> dto = artistService.getTop(max);
        return dto != null ? ResponseWrapper.ok(dto, ArtistResponse.SUCCESS_READ) :
                ResponseWrapper.notFound(ArtistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArtistViews.All.class)
    @DeleteMapping("{id}")
    public ResponseWrapper delete(@PathVariable("id") Long id) {
        boolean deleted = artistService.deleteById(id);
        return deleted ? ResponseWrapper.success(ArtistResponse.SUCCESS_DELETED) :
                ResponseWrapper.notFound(ArtistResponse.ERROR_NOT_FOUND);
    }

    @JsonView(ArtistViews.All.class)
    @GetMapping("music")
    public ResponseWrapper artistPage(@RequestParam(value = "page") Optional<Integer> page,
                                      @RequestParam(value = "artistName", required = false) String artistName) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Specification<Artist> spec = Specification.where(null);
        if (artistName != null) spec.and(ArtistSpecs.artistNameContains(artistName));

        Page<Artist> pages = artistService.getArtistsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        if (pages == null) return ResponseWrapper.notFound(ArtistResponse.ERROR_NOT_FOUND);

        List<Artist> artists = pages.getContent();
        List<ArtistDto> dtos = artists.stream()
                .map(artist -> artistService.convertToDto(artist))
                .collect(Collectors.toList());
        return ResponseWrapper.ok(dtos, ArtistResponse.SUCCESS_READ);
    }

    @JsonView(ArtistViews.All.class)
    @PostMapping
    public ResponseWrapper save(ArtistDto dto) {
        Artist convertedEntity = artistService.convertToEntity(dto);
        Artist resultEntity = artistService.saveOrUpdate(convertedEntity);
        return resultEntity != null ? ResponseWrapper.success(ArtistResponse.SUCCESS_CREATED) :
                ResponseWrapper.notFound(ArtistResponse.ERROR_NOT_FOUND);
    }
}

