package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.blog.Comment;

import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
