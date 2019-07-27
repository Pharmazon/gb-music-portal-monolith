package ru.geekbrains.musicportal.service.comment;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.musicportal.dto.comment.CommentDto;
import ru.geekbrains.musicportal.entity.comment.Comment;
import ru.geekbrains.musicportal.repository.CommentRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Comment saveOrUpdate(Comment entity) {
        return commentRepository.save(entity);
    }

    @Override
    public Optional<Comment> findOneEntityById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public CommentDto findOneDtoById(Long id) {
        return commentRepository.findOneById(id);
    }

    @Override
    public Collection<CommentDto> findAllDtos() {
        return commentRepository.findAllByIdNotNull();
    }

    @Override
    public Collection<Comment> findAll() {
        return (Collection<Comment>) commentRepository.findAll();
    }

    @Override
    public Comment convertToEntity(CommentDto dto) {
        return modelMapper.map(dto, Comment.class);
    }

    @Override
    public CommentDto convertToDto(Comment entity) {
        return modelMapper.map(entity, CommentDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        commentRepository.deleteById(id);
        return true;
    }
}
