package ru.geekbrains.musicportal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.musicportal.dto.CommentDto;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.service.comment.CommentServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping (value = "api/miraculous/comment")
public class CommentController {

    CommentServiceImpl commentService;

    @Autowired
    public void setCommentService(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "list")
    public Iterable<Comment> comments() {
        return commentService.findAll();
    }

    @RequestMapping (value = "create", method = RequestMethod.POST)
    public Comment createComment(@Valid CommentDto commentDto) {
        return commentService.save(commentDto);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteById(id);
        return "Comment Deleted";
    }
}
