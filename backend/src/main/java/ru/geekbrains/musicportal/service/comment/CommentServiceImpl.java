package ru.geekbrains.musicportal.service.comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.CommentDto;
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

    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment save(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setAuthor(commentDto.getAuthor());
        comment.setContent(commentDto.getContent());
        comment.setEntityId(commentDto.getEntityId());
        comment.setTypeCommentedEntity(commentDto.getEntity());
        return commentRepository.save(comment);
    }

    public Comment update(CommentDto commentDto, Long id) {
        return commentRepository.updateById(id, commentDto);
    }
}
