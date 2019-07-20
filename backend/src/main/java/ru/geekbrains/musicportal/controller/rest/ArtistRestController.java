package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.artist.ArtistDto;
import ru.geekbrains.musicportal.entity.artist.Artist;
import ru.geekbrains.musicportal.marker.ArtistViews;
import ru.geekbrains.musicportal.service.artist.ArtistService;
import ru.geekbrains.musicportal.specification.ArtistSpecs;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

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
    public Collection<ArtistDto> getAll() {
        return artistService.findAllDto();
    }

    @JsonView(ArtistViews.All.class)
    @GetMapping("{id}")
    public ArtistDto getOneById(@PathVariable("id") Long id) {
        return artistService.findOneDtoById(id);
    }

    @JsonView(ArtistViews.All.class)
    @PutMapping
    public void updateArtist(ArtistDto dto) {
        Optional<Artist> artist = artistService.findOneEntityById(dto.getId());
        artist.ifPresent(value -> artistService.saveOrUpdate(value));
    }

    @JsonView(ArtistViews.List.class)
    @GetMapping("/filter")
    public String artistPage(Model model,
                             @RequestParam(value = "page") Optional<Integer> page,
                             @RequestParam(value = "artistName", required = false) String artistName) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Specification<Artist> spec = Specification.where(null);
        if (artistName != null) spec.and(ArtistSpecs.artistNameContains(artistName));

        Page<Artist> artists = artistService.getArtistsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        model.addAttribute("artists", artists.getContent());
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPage", artists.getTotalPages());
        model.addAttribute("artistName", artistName);
        return "Success";
    }
}

