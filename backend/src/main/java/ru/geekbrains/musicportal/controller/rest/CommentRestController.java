package ru.geekbrains.musicportal.controller.rest;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.blog.CommentDto;
import ru.geekbrains.musicportal.dto.marker.CommentViews;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.service.comment.CommentServiceImpl;

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

    @JsonView (CommentViews.List.class)
    @GetMapping
    public Collection<CommentDto> comments() {
        return commentService.findAllDto();
    }

    @JsonView (CommentViews.List.class)
    @PostMapping
    public Comment createComment(@Valid CommentDto commentDto) {
        return commentService.save(commentDto);
    }

    @JsonView (CommentViews.List.class)
    @PutMapping("{id}")
    public Comment updateComment(@PathVariable Long id,
                                 @Valid CommentDto commentDto) {
        return commentService.update(commentDto, id);
    }

    @JsonView (CommentViews.List.class)
    @DeleteMapping("{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
        return "Comment Deleted";
    }
}
