package ru.geekbrains.musicportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.service.comment.CommentServiceImpl;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping (value = "api/miraculous/comment")
public class CommentRestController {

    private CommentServiceImpl commentService;

    @Autowired
    public CommentRestController(CommentServiceImpl commentService) {
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

    @PutMapping
    public Comment updateComment(@Valid CommentDto commentDto) {
        return commentService.update(commentDto);
    }

    @DeleteMapping(value = "{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
        return "Comment Deleted";
    }
}
