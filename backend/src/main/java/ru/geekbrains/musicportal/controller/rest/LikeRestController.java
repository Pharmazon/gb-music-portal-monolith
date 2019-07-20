package ru.geekbrains.musicportal.controller.rest;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.blog.LikeDto;
import ru.geekbrains.musicportal.entity.blog.Like;
import ru.geekbrains.musicportal.marker.LikeViews;
import ru.geekbrains.musicportal.service.blog.LikeServiceImpl;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/likes")
public class LikeRestController {

    private LikeServiceImpl likeService;

    @Autowired
    public LikeRestController(LikeServiceImpl likeService) {
        this.likeService = likeService;
    }

    @JsonView(LikeViews.List.class)
    @GetMapping
    public Iterable<Like> getAll() {
        return likeService.findAll();
    }

    @JsonView(LikeViews.List.class)
    @GetMapping("{id}")
    public Optional<Like> getOneById(@PathVariable Long id) {
        return likeService.findById(id);
    }

    @JsonView(LikeViews.List.class)
    @DeleteMapping("{id}")
    public String deleteOneById(@PathVariable Long id) {
        likeService.deleteById(id);
        return "Like Deleted";
    }

    @JsonView(LikeViews.List.class)
    @PostMapping ("{id}")
    public Like createLike(@PathVariable Long id, @Valid LikeDto likeDto) {
        return likeService.save(likeDto);
    }

}
