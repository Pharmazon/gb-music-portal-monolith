package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.dto.CommentDto;
import ru.geekbrains.musicportal.entity.blog.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

//    Comment updateById(Long id, CommentDto comment);
}
