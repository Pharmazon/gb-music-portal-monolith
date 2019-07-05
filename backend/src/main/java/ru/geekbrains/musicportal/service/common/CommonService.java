package ru.geekbrains.musicportal.service.common;

import ru.geekbrains.musicportal.entity.blog.Comment;
import ru.geekbrains.musicportal.entity.common.AbstractEntity;

import java.util.Optional;

public interface CommonService<T extends AbstractEntity, D> {

    T save(T entity);

    Optional<T> findById(Long id);

    Iterable<Comment> findAll();

    T convertToEntity(D dto);
}
