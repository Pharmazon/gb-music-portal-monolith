package ru.geekbrains.musicportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.LikeDto;
import ru.geekbrains.musicportal.entity.blog.Like;
import ru.geekbrains.musicportal.service.like.LikeServiceImpl;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/miraculous/like")
public class LikeRestController {

    private LikeServiceImpl likeService;

    @Autowired
    public LikeRestController(LikeServiceImpl likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public Iterable<Like> likes() {
        return likeService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Like> getLike(@PathVariable Long id) {
        return likeService.findById(id);
    }

    @DeleteMapping("{id}")
    public String deleteLike(@PathVariable Long id) {
        likeService.deleteById(id);
        return "Like Deleted";
    }

    @PostMapping
    public Like createLike(@Valid LikeDto like) {
        return likeService.save(like);
    }
}
