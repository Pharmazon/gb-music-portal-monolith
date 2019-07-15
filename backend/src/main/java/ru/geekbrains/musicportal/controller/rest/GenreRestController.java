package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.track.GenreDto;
import ru.geekbrains.musicportal.entity.track.Genre;
import ru.geekbrains.musicportal.marker.GenreViews;
import ru.geekbrains.musicportal.service.track.GenreService;
import ru.geekbrains.musicportal.specification.GenreSpecs;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/genres")
public class GenreRestController {

    private final int INITIAL_PAGE = 50;
    private final int PAGE_SIZE = 50;
    private GenreService genreService;

    @Autowired
    public GenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @JsonView(GenreViews.List.class)
    @GetMapping
    public Collection<GenreDto> getAll() {
        return genreService.findAllDto();
    }

    @JsonView(GenreViews.Single.class)
    @GetMapping("{id}")
    public GenreDto getOneById(@PathVariable("id") Long id) {
        return genreService.findOneDtoById(id);
    }

    @JsonView(GenreViews.List.class)
    @GetMapping("/filter")
    public String genrePage(Model model,
                            @RequestParam(value = "page") Optional<Integer> page,
                            @RequestParam(value = "genreName", required = false) String genreName) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Specification<Genre> spec = Specification.where(null);
        if (genreName != null) spec.and(GenreSpecs.genreNameContains(genreName));

        Page<Genre> genres = genreService.getGenresWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        model.addAttribute("genres", genres.getContent());
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPage", genres.getTotalPages());
        model.addAttribute("genreName", genreName);
        return "Success";
    }
}
