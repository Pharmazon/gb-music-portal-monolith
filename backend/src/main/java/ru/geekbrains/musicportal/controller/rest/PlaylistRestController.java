package ru.geekbrains.musicportal.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.blog.LikeDto;
import ru.geekbrains.musicportal.dto.playlist.PlaylistDto;
import ru.geekbrains.musicportal.entity.blog.Like;
import ru.geekbrains.musicportal.entity.playlist.Playlist;
import ru.geekbrains.musicportal.enums.EntityLikeEnum;
import ru.geekbrains.musicportal.marker.LikeViews;
import ru.geekbrains.musicportal.marker.PlaylistViews;
import ru.geekbrains.musicportal.service.blog.LikeServiceImpl;
import ru.geekbrains.musicportal.service.playlist.PlaylistService;
import ru.geekbrains.musicportal.specification.PlaylistSpecs;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/albums")
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

    @JsonView(PlaylistViews.List.class)
    @GetMapping
    public Collection<PlaylistDto> getAll() {
        return playlistService.findAllDto();
    }

    @JsonView(PlaylistViews.Single.class)
    @GetMapping("{id}")
    public PlaylistDto getOneById(@PathVariable("id") Long id) {
        return playlistService.findOneDtoById(id);
    }

    @JsonView(PlaylistViews.All.class)
    @GetMapping("/top-15-albums")
    public Collection<PlaylistDto> getTopByLikes() {
        return playlistService.getTop(15);
    }

    @JsonView(PlaylistViews.List.class)
    @GetMapping("/filter")
    public String playlistPage(Model model,
                            @RequestParam(value = "page") Optional<Integer> page,
                            @RequestParam(value = "playlistName", required = false) String playlistName) {
        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Specification<Playlist> spec = Specification.where(null);
        if (playlistName != null) spec.and(PlaylistSpecs.playlistNameContains(playlistName));

        Page<Playlist> playlists = playlistService.getPlaylistsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);
        model.addAttribute("playlists", playlists.getContent());
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPages", playlists.getTotalPages());
        model.addAttribute("playlistName", playlistName);
        return "Success";
    }
    @JsonView(LikeViews.List.class)
    @PostMapping ("albums/add-like/{id}")
    public Like createLikeAlbum(@PathVariable Long id, @Valid LikeDto likeDto) {
        likeDto.setEntityId(id);
        likeDto.setEntity(EntityLikeEnum.ALBUM);
        return likeService.save(likeDto);
    }
}