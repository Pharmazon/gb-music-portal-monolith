package ru.geekbrains.musicportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.LikeDto;
import ru.geekbrains.musicportal.entity.blog.Like;
import ru.geekbrains.musicportal.service.like.LikeServiceImpl;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping (value = "api/miraculous/like")
public class LikeController {

    LikeServiceImpl likeService;

    @Autowired
    public void setLikeService(LikeServiceImpl likeService) {
        this.likeService = likeService;
    }

    @GetMapping(value = "list")
    public Iterable<Like> likes() {
        return likeService.findAll();
    }

    @GetMapping(value = "{id}")
    public Optional<Like> getLike(@PathVariable Long id) {
        return likeService.findById(id);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String deleteLike(@PathVariable Long id) {
        likeService.deleteById(id);
        return "Like Deleted";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public Like createLike(@Valid LikeDto like) {
        return likeService.save(like);
    }
}
