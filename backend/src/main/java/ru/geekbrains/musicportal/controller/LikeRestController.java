package ru.geekbrains.musicportal.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.like.LikeDto;
import ru.geekbrains.musicportal.entity.like.Like;
import ru.geekbrains.musicportal.marker.LikeViews;
import ru.geekbrains.musicportal.response.LikeResponse;
import ru.geekbrains.musicportal.response.common.ResponseWrapper;
import ru.geekbrains.musicportal.service.like.LikeServiceImpl;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/likes")
public class LikeRestController {

    private LikeServiceImpl likeService;

    @Autowired
    public LikeRestController(LikeServiceImpl likeService) {
        this.likeService = likeService;
    }

    @JsonView(LikeViews.All.class)
    @GetMapping
    public ResponseWrapper getAll() {
        Collection<Like> likes = likeService.findAll();
        if (likes != null) {
            List<LikeDto> dtos = likes.stream()
                    .map(LikeDto::new)
                    .collect(Collectors.toList());
            return ResponseWrapper.ok(dtos, LikeResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(LikeResponse.ERROR_NOT_FOUND);
    }

    @JsonView(LikeViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper getOneById(@PathVariable("id") Long id) {
        Optional<Like> optional = likeService.findOneEntityById(id);
        if (optional.isPresent()) {
            LikeDto dto = new LikeDto(optional.get());
            return ResponseWrapper.ok(dto, LikeResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(LikeResponse.ERROR_NOT_FOUND);
    }

    @JsonView(LikeViews.All.class)
    @DeleteMapping("{id}")
    public ResponseWrapper deleteOneById(@PathVariable("id") Long id) {
        boolean deleted = likeService.deleteById(id);
        if (deleted) return ResponseWrapper.ok(null, LikeResponse.SUCCESS_DELETED);
        return ResponseWrapper.notFound(LikeResponse.ERROR_DELETED);
    }

    @JsonView(LikeViews.All.class)
    @PostMapping
    public ResponseWrapper createLike(@Valid LikeDto likeDto) {
        Like converted = likeService.convertToEntity(likeDto);
        Like like = likeService.saveOrUpdate(converted);
        if (converted != null && like != null) return ResponseWrapper.ok(null, LikeResponse.SUCCESS_CREATED);
        return ResponseWrapper.notFound(LikeResponse.ERROR_CREATED);
    }

}
