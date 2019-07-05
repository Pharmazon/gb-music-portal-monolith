package ru.geekbrains.musicportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.CommentDto;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.service.comment.CommentServiceImpl;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping (value = "api/miraculous/comment")
public class CommentController {

    private CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public Iterable<Comment> comments() {
        return commentService.findAll();
    }

    @PostMapping
    public Comment createComment(@Valid CommentDto commentDto) {
        return commentService.save(commentDto);
    }

    @PutMapping(value = "{id}")
    public Comment updateComment(@PathVariable Long id, @Valid CommentDto commentDto) {
        return commentService.update(commentDto, id);
    }

    @DeleteMapping(value = "{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
        return "Comment Deleted";
    }
}
