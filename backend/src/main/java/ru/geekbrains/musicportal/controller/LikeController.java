package ru.geekbrains.musicportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.LikeDto;
import ru.geekbrains.musicportal.entity.blog.Like;
import ru.geekbrains.musicportal.service.like.LikeServiceImpl;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class LikeController {

    LikeServiceImpl likeService;

    @Autowired
    public void setLikeService(LikeServiceImpl likeService) {
        this.likeService = likeService;
    }

    @RequestMapping(value = "api/like/list", method = RequestMethod.GET)
    public Iterable<Like> likes() {
        return likeService.findAll();
    }

    @RequestMapping(value = "api/like/{id}", method = RequestMethod.GET)
    public Optional<Like> getLike(@PathVariable Long id) {
        return likeService.findById(id);
    }

    @PostMapping(value = "api/like/delete/{id}")
    public String deleteLike(@PathVariable Long id) {
        likeService.deleteById(id);
        return "Like Deleted";
    }

    @PostMapping(value = "api/like/create")
    public Like createLike(@Valid LikeDto like) {
        return likeService.save(like);
    }
}
