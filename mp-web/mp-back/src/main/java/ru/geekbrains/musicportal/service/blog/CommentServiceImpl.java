package ru.geekbrains.musicportal.service.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.repository.CommentRepository;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment save(Comment entity) {
        return commentRepository.save(entity);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }
}