package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.band.BandDto;
import ru.geekbrains.musicportal.entity.band.Band;
import ru.geekbrains.musicportal.marker.BandViews;
import ru.geekbrains.musicportal.service.band.BandService;
import ru.geekbrains.musicportal.specification.BandSpecs;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/artists")
public class BandRestController {

    private final int INITIAL_PAGE = 50;
    private final int PAGE_SIZE = 50;

    private BandService bandService;

    @Autowired
    public BandRestController(BandService bandService) {
        this.bandService = bandService;
    }

    @JsonView(BandViews.List.class)
    @GetMapping
    public Collection<BandDto> getAll() {
        return bandService.findAllDto();
    }

    @JsonView(BandViews.All.class)
    @GetMapping("{artistId}")
    public BandDto getOneById(@PathVariable("artistId") Long artistId) {
        return bandService.findOneDtoById(artistId);
    }

    @JsonView(BandViews.List.class)
    @GetMapping("/filter")
    public String artistPage(Model model,
                             @RequestParam(value = "page") Optional<Integer> page,
                             @RequestParam(value = "artistName", required = false) String artistName) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Specification<Band> spec = Specification.where(null);
        if (artistName != null) spec.and(BandSpecs.bandNameContains(artistName));

        Page<Band> bands = bandService.getBandsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        model.addAttribute("bands", bands.getContent());
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPage", bands.getTotalPages());
        model.addAttribute("artistName", artistName);
        return "Success";
    }
}

