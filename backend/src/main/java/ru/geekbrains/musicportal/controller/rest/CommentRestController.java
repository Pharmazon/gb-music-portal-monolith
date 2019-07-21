package ru.geekbrains.musicportal.controller.rest;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.blog.CommentDto;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.marker.CommentViews;
import ru.geekbrains.musicportal.response.CommentResponse;
import ru.geekbrains.musicportal.response.common.ResponseWrapper;
import ru.geekbrains.musicportal.service.blog.CommentServiceImpl;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentRestController {

    private CommentServiceImpl commentService;

    @Autowired
    public CommentRestController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @JsonView(CommentViews.All.class)
    @GetMapping
    public ResponseWrapper<Collection<CommentDto>> getAll() {
        Collection<CommentDto> dtos = commentService.findAllDtos();
        if (dtos != null) {
            return ResponseWrapper.ok(dtos, CommentResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(CommentResponse.ERROR_NOT_FOUND);
    }

    @JsonView(CommentViews.All.class)
    @GetMapping("{id}")
    public ResponseWrapper<CommentDto> getOneById(@PathVariable("id") Long id) {
        CommentDto dto = commentService.findOneDtoById(id);
        if (dto != null) {
            return ResponseWrapper.ok(dto, CommentResponse.SUCCESS_READ);
        }
        return ResponseWrapper.notFound(CommentResponse.ERROR_NOT_FOUND);
    }

    @JsonView(CommentViews.All.class)
    @PostMapping
    public ResponseWrapper<CommentDto> createComment(@Valid CommentDto commentDto) {
        Comment converted = commentService.convertToEntity(commentDto);
        Comment comment = commentService.saveOrUpdate(converted);
        if (comment != null && converted != null) {
            return ResponseWrapper.ok(commentDto, CommentResponse.SUCCESS_CREATED);
        }
        return ResponseWrapper.notFound(CommentResponse.ERROR_NOT_FOUND);
    }

    @JsonView(CommentViews.All.class)
    @PutMapping
    public ResponseWrapper<CommentDto> updateComment(@Valid CommentDto commentDto) {
        Comment converted = commentService.convertToEntity(commentDto);
        Comment comment = commentService.saveOrUpdate(converted);
        if (converted != null && comment != null) {
            return ResponseWrapper.ok(commentDto, CommentResponse.SUCCESS_UPDATED);
        }
        return ResponseWrapper.notFound(CommentResponse.ERROR_NOT_FOUND);
    }

    @JsonView(CommentViews.All.class)
    @DeleteMapping("{id}")
    public ResponseWrapper deleteComment(@PathVariable("id") Long id) {
        boolean deleted = commentService.deleteById(id);
        if (deleted) return ResponseWrapper.success(CommentResponse.SUCCESS_DELETED);
        return ResponseWrapper.notFound(CommentResponse.ERROR_NOT_FOUND);
    }
}
