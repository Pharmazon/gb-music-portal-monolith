package ru.geekbrains.musicportal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.musicportal.entity.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
}
