package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.comment.CommentDto;
import ru.geekbrains.musicportal.entity.comment.Comment;

@Repository
public interface CommentRepository extends CommonRepository<CommentDto>, CrudRepository<Comment, Long> {

}
