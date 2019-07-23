package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.genre.GenreDto;
import ru.geekbrains.musicportal.entity.genre.Genre;
import ru.geekbrains.musicportal.marker.GenreViews;
import ru.geekbrains.musicportal.response.GenreResponse;
import ru.geekbrains.musicportal.response.common.ResponseWrapper;
import ru.geekbrains.musicportal.service.genre.GenreService;
import ru.geekbrains.musicportal.specification.GenreSpecs;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @JsonView(GenreViews.All.class)
    @GetMapping
    public ResponseWrapper getAll() {
        Collection<GenreDto> dtos = genreService.findAllDtos();
        if (dtos != null) {
            return ResponseWrapper.ok(dtos, GenreResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(GenreResponse.ERROR_NOT_FOUND);
    }

    @JsonView(GenreViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper getOneById(@PathVariable("id") Long id) {
        GenreDto dto = genreService.findOneDtoById(id);
        if (dto != null) {
            return ResponseWrapper.ok(dto, GenreResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(GenreResponse.ERROR_NOT_FOUND);
    }

    @JsonView(GenreViews.All.class)
    @GetMapping("filter")
    public ResponseWrapper genrePage(@RequestParam(value = "page") Optional<Integer> page,
                                     @RequestParam(value = "name", required = false) String name) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        Specification<Genre> spec = Specification.where(null);
        if (name != null) spec.and(GenreSpecs.genreNameContains(name));
        Page<Genre> genres = genreService.getGenresWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        if (genres != null) {
            List<Genre> content = genres.getContent();
            Collection<GenreDto> dtos = content.stream()
                    .map(genre -> genreService.convertToDto(genre))
                    .collect(Collectors.toList());
            return ResponseWrapper.ok(dtos, GenreResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(GenreResponse.ERROR_NOT_FOUND);
    }
}
