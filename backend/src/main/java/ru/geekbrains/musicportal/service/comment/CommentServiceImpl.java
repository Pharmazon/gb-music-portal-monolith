package ru.geekbrains.musicportal.service.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.blog.CommentDto;
import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.repository.CommentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
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
    public Optional<Comment> findOneEntityById(Long id) {
        return commentRepository.findById(id);
    }

    public Collection<CommentDto> findAllDto() {
        return commentRepository.findAllByIdNotNull();
    }

    @Override
    public CommentDto findOneDtoById(Long id) {
        return commentRepository.findOneById(id);
    }

    @Override
    public Comment convertToEntity(CommentDto dto) {
        return null;
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
        Comment comment = convertToEntity(commentRepository.findOneById(id));
        if (comment != null) {
            save(commentDto);
        }
        return null;
    }
}
