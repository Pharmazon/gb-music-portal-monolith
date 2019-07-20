package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.artist.ArtistProfileDto;
import ru.geekbrains.musicportal.entity.artist.Artist;
import ru.geekbrains.musicportal.marker.ArtistViews;
import ru.geekbrains.musicportal.service.artist.ArtistService;
import ru.geekbrains.musicportal.specification.ArtistSpecs;

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

    @JsonView(ArtistViews.List.class)
    @GetMapping
    public Collection<ArtistProfileDto> getAll() {
        return artistService.findAllDto();
    }

    @JsonView(ArtistViews.All.class)
    @GetMapping("{id}")
    public ArtistProfileDto getOneById(@PathVariable("id") Long id) {
        return artistService.findOneDtoById(id);
    }

    @PutMapping
    public void saveOrUpdate(ArtistProfileDto dto) {
        Optional<Artist> artist = artistService.findOneEntityById(dto.getId());
        artist.ifPresent(value -> artistService.saveOrUpdate(value));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        artistService.deleteById(id);
    }

    @JsonView(ArtistViews.All.class)
    @GetMapping("music/{id}")
    public Collection<ArtistProfileDto> getMusic(Model model,
                                                 @RequestParam(value = "page") Optional<Integer> page,
                                                 @RequestParam(value = "artistName", required = false) String artistName) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Specification<Artist> spec = Specification.where(null);
        if (artistName != null) spec.and(ArtistSpecs.artistNameContains(artistName));

        Page<Artist> pages = artistService.getArtistsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        List<Artist> artists = pages.getContent();
        List<ArtistProfileDto> dtos = artists.stream()
                .map(artist -> artistService.convertToDto(artist))
                .collect(Collectors.toList());
        model.addAttribute("artists", artists);
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPages", pages.getTotalPages());
        model.addAttribute("artistName", artistName);
        return dtos;
    }

}

